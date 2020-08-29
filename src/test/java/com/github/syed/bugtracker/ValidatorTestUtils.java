package com.github.syed.bugtracker;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.Optional;

public class ValidatorTestUtils {

    public static Optional<ObjectError> findErrorCode(Errors errors, String errorMessage) {
        return errors.getAllErrors().stream().filter(error -> errorMessage.equals(error.getDefaultMessage())).findFirst();
    }
}
