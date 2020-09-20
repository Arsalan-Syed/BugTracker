package com.github.syed.bugtracker.issue.exception;

public class InvalidIssueStatusException extends RuntimeException {
    public InvalidIssueStatusException(String msg){
        super(msg);
    }
}
