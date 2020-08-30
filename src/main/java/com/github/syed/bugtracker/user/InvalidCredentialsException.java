package com.github.syed.bugtracker.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String msg){
        super(msg);
    }
}
