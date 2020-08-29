package com.github.syed.bugtracker;

import com.github.syed.bugtracker.user.CreateUserRequest;
import com.github.syed.bugtracker.user.User;
import com.github.syed.bugtracker.user.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.Optional;

import static liquibase.util.StringUtils.hasLowerCase;
import static liquibase.util.StringUtils.hasUpperCase;

@Component
public class CreateUserRequestValidator implements Validator {

    UserRepository userRepository;

    public CreateUserRequestValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(CreateUserRequest.class);
    }

    @Override
    public void validate(Object object, Errors errors) {
        CreateUserRequest request = (CreateUserRequest) object;

        validateUsername(errors, request.getUsername());
        validatePassword(errors, request.getPassword(), request.getMatchPassword());
        validateEmail(errors, request.getEmail());
    }
    //shouldnt reject method be called on the object itself??

    private void validateUsername(Errors errors, String username) {
        if (username.length() < 5){
            errors.reject(username, "username too short");
        }
        if (username.length() > 20){
            errors.reject(username, "username too long");
        }
        if(StringUtils.containsWhitespace(username)){
            errors.reject(username, "username has spaces");
        }

        checkForExistingUsername(errors, username);
    }

    private void checkForExistingUsername(Errors errors, String username) {
        Optional<User> userOptional = userRepository.findOne(
                Example.of(
                        User.builder().username(username).build()
                )
        );

        if(userOptional.isPresent()){
            errors.reject(username, "username already exists");
        }
    }

    private void validatePassword(Errors errors, String password, String matchPassword) {
        if(StringUtils.isEmpty(password)){
            errors.reject(password, "empty password");
            return;
        }

        if(!password.equals(matchPassword)){
            errors.reject(password, "password does not not match");
        }

        if(password.length() < 8){
            errors.reject(password, "password too short");
        }

        if(!hasLowerCase(password)){
            errors.reject(password, "password needs at least one lowercase letter");
        }

        if(!hasUpperCase(password)){
            errors.reject(password, "password needs at least one uppercase letter");
        }

        if (!stringContainsAnyOf(password,"!#@#£$.:,%&/()")) {
            errors.reject(password, "password needs at least one special symbol");
        }

        if(!password.matches(".*\\d.*")){
            errors.reject(password, "password needs at least one number");
        }
    }

    private void validateEmail(Errors errors, String email) {
        Optional<User> userOptional = userRepository.findOne(
                Example.of(
                        User.builder().email(email).build()
                )
        );

        if(userOptional.isPresent()){
            errors.reject(email, "email already exists");
        }
    }

    private boolean stringContainsAnyOf(String string, String characters){
        return Arrays.stream(characters.split("")).anyMatch(string::contains);
    }
}
