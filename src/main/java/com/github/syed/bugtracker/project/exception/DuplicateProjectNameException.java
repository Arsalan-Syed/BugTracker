package com.github.syed.bugtracker.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateProjectNameException extends RuntimeException {
    public DuplicateProjectNameException(String message){
        super(message);
    }
}