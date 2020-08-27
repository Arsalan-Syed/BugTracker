package com.github.syed.bugtracker.user;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class UsernameValidatorTest {

    UsernameValidator validator;

    @Before
    public void setup(){
        validator = new UsernameValidator();
    }

    @Test
    public void shouldRejectUsernameIfNull(){
        String username = null;
        Errors errors = new BeanPropertyBindingResult(username, "username");
        validator.validate(username, errors);
        Optional<ObjectError> error = findErrorCode(errors, "empty username");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectUsernameIfEmpty(){
        String username = "";
        Errors errors = new BeanPropertyBindingResult(username, "username");
        validator.validate(username, errors);
        Optional<ObjectError> error = findErrorCode(errors, "empty username");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectUsernameIfLessThanFiveCharacters(){
        String username = "abcd";
        Errors errors = new BeanPropertyBindingResult(username, "username");
        validator.validate(username, errors);
        Optional<ObjectError> error = findErrorCode(errors, "username too short");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldAllowValidUsername(){
        String username = "spicy_tuna_123";
        Errors errors = new BeanPropertyBindingResult(username, "username");
        validator.validate(username, errors);
        assertThat(errors.getAllErrors(), empty());
    }

    private Optional<ObjectError> findErrorCode(Errors errors, String errorMessage) {
        return errors.getAllErrors().stream().filter(error -> errorMessage.equals(error.getDefaultMessage())).findFirst();
    }

}