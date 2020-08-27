package com.github.syed.bugtracker.user;

import liquibase.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class PasswordValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object object, Errors errors) {
        String password = (String) object;
        if(StringUtils.isEmpty(password)){
            errors.reject(password, "empty password");
            return;
        }

        if(password.length() < 8){
            errors.reject(password, "password too short");
        }

        if(!StringUtils.hasLowerCase(password)){
            errors.reject(password, "password needs at least one lowercase letter");
        }

        if(!StringUtils.hasUpperCase(password)){
            errors.reject(password, "password needs at least one uppercase letter");
        }

        if (!stringContainsAnyOf(password,"!#@#£$.:,%&/()")) {
            errors.reject(password, "password needs at least one special symbol");
        }

        if(!password.matches(".*\\d.*")){
            errors.reject(password, "password needs at least one number");
        }
    }

    private boolean stringContainsAnyOf(String string, String characters){
        return Arrays.stream(characters.split("")).anyMatch(string::contains);
    }
}
