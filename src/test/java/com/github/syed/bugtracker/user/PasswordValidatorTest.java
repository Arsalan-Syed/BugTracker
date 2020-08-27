package com.github.syed.bugtracker.user;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.Optional;

import static com.github.syed.bugtracker.ValidatorTestUtils.findErrorCode;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PasswordValidatorTest {

    PasswordValidator validator;

    @Before
    public void setup(){
        validator = new PasswordValidator();
    }

    @Test
    public void shouldRejectPasswordIfNull(){
        String password = null;
        Errors errors = new BeanPropertyBindingResult(password, "password");
        validator.validate(password, errors);
        Optional<ObjectError> error = findErrorCode(errors, "empty password");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectPasswordIfEmpty(){
        String password = "";
        Errors errors = new BeanPropertyBindingResult(password, "password");
        validator.validate(password, errors);
        Optional<ObjectError> error = findErrorCode(errors, "empty password");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectPasswordIfLessThanEightCharacters(){
        String password = "1234567";
        Errors errors = new BeanPropertyBindingResult(password, "password");
        validator.validate(password, errors);
        Optional<ObjectError> error = findErrorCode(errors, "password too short");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectPasswordIfDoesNotContainAtLeastOneLowercaseLetter(){
        String password = "12345678";
        Errors errors = new BeanPropertyBindingResult(password, "password");
        validator.validate(password, errors);
        Optional<ObjectError> error = findErrorCode(errors, "password needs at least one lowercase letter");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectPasswordIfDoesNotContainAtLeastOneUppercaseLetter() {
        String password = "12345678";
        Errors errors = new BeanPropertyBindingResult(password, "password");
        validator.validate(password, errors);
        Optional<ObjectError> error = findErrorCode(errors, "password needs at least one uppercase letter");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectPasswordIfDoesNotContainAtLeastOneNumber() {
        String password = "abcdefgh";
        Errors errors = new BeanPropertyBindingResult(password, "password");
        validator.validate(password, errors);
        Optional<ObjectError> error = findErrorCode(errors, "password needs at least one number");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectPasswordIfDoesNotContainAtLeastOneSpecialSymbol(){
        String password = "12345678";
        Errors errors = new BeanPropertyBindingResult(password, "password");
        validator.validate(password, errors);
        Optional<ObjectError> error = findErrorCode(errors, "password needs at least one special symbol");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    @Ignore //search through a dictionary of words or common passwords
    public void shouldRejectPasswordIfItsTooCommon(){
        String password = "password";
        Errors errors = new BeanPropertyBindingResult(password, "password");
        validator.validate(password, errors);
        Optional<ObjectError> error = findErrorCode(errors, "password is a common word");
        assertThat(error.isPresent(), is(true));
    }

}