package com.github.syed.bugtracker.user;


import liquibase.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UsernameValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object object, Errors errors) {
        String username = (String) object;

        if(StringUtils.isEmpty(username)){
            errors.reject(username, "empty username");
        } else if (username.length() < 5){
            errors.reject(username, "username too short");
        }
    }
}
