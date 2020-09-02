package com.github.syed.bugtracker.issue;

public class InvalidIssueStatusException extends RuntimeException {
    public InvalidIssueStatusException(String msg){
        super(msg);
    }
}
