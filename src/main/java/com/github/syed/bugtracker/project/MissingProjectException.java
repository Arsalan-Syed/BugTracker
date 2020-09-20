package com.github.syed.bugtracker.project;

public class MissingProjectException extends Throwable {
    public MissingProjectException(String msg) {
        super(msg);
    }
}
