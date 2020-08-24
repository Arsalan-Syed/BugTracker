package com.github.syed.bugtracker.issue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IssueNotFoundException extends RuntimeException {
    public IssueNotFoundException(String msg){
        super(msg);
    }
}
