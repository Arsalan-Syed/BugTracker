package com.github.syed.bugtracker;

import com.github.syed.bugtracker.user.CreateUserRequest;
import com.github.syed.bugtracker.user.User;
import com.github.syed.bugtracker.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.Optional;

import static com.github.syed.bugtracker.ValidatorTestUtils.findErrorCode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.mockito.Mockito.any;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserRequestValidatorTest {

    @Mock
    UserRepository userRepository;

    CreateUserRequestValidator validator;

    @Before
    public void setup(){
       validator = new CreateUserRequestValidator(userRepository);
    }

    @Test
    public void shouldRejectUsernameIfLessThanFiveCharacters(){
        CreateUserRequest request = CreateUserRequest.builder()
                .username("abcd")
                .build();

        Errors errors = new BeanPropertyBindingResult(request, "username");
        validator.validate(request, errors);
        Optional<ObjectError> error = findErrorCode(errors, "username too short");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectUsernameIfMoreThanTwentyCharacters(){
        CreateUserRequest request = CreateUserRequest.builder()
                .username("abcd abcd abcd abcd abcd abcd")
                .build();

        Errors errors = new BeanPropertyBindingResult(request, "username");
        validator.validate(request, errors);
        Optional<ObjectError> error = findErrorCode(errors, "username too long");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectUsernameIfContainsSpaces(){
        CreateUserRequest request = CreateUserRequest.builder()
                .username("abcd  efgh")
                .build();

        Errors errors = new BeanPropertyBindingResult(request, "username");
        validator.validate(request, errors);
        Optional<ObjectError> error = findErrorCode(errors, "username has spaces");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldAllowValidRequest(){
        CreateUserRequest request = CreateUserRequest.builder()
                .username("spicy_tuna_123")
                .password("somePassword#1")
                .matchPassword("somePassword#1")
                .email("someone@email.com")
                .build();

        Errors errors = new BeanPropertyBindingResult(request, "username");
        validator.validate(request, errors);
        assertThat(errors.getAllErrors(), empty());
    }

    @Test
    public void shouldRejectPasswordIfLessThanEightCharacters(){
        CreateUserRequest request = CreateUserRequest.builder()
                .username("validUsername")
                .password("1234567")
                .matchPassword("1234567")
                .build();

        Errors errors = new BeanPropertyBindingResult(request, "password");
        validator.validate(request, errors);
        Optional<ObjectError> error = findErrorCode(errors, "password too short");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectPasswordIfDoesNotContainAtLeastOneLowercaseLetter(){
        CreateUserRequest request = CreateUserRequest.builder()
                .username("validUsername")
                .password("12345678")
                .matchPassword("12345678")
                .build();

        Errors errors = new BeanPropertyBindingResult(request, "password");
        validator.validate(request, errors);
        Optional<ObjectError> error = findErrorCode(errors, "password needs at least one lowercase letter");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectPasswordIfDoesNotContainAtLeastOneUppercaseLetter() {
        CreateUserRequest request = CreateUserRequest.builder()
                .username("validUsername")
                .password("12345678")
                .matchPassword("12345678")
                .build();

        Errors errors = new BeanPropertyBindingResult(request, "password");
        validator.validate(request, errors);
        Optional<ObjectError> error = findErrorCode(errors, "password needs at least one uppercase letter");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectPasswordIfDoesNotContainAtLeastOneNumber() {
        CreateUserRequest request = CreateUserRequest.builder()
                .username("validUsername")
                .password("abcdefgh")
                .matchPassword("abcdefgh")
                .build();

        Errors errors = new BeanPropertyBindingResult(request, "password");
        validator.validate(request, errors);
        Optional<ObjectError> error = findErrorCode(errors, "password needs at least one number");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectPasswordIfDoesNotContainAtLeastOneSpecialSymbol(){
        CreateUserRequest request = CreateUserRequest.builder()
                .username("validUsername")
                .password("12345678")
                .matchPassword("12345678")
                .build();

        Errors errors = new BeanPropertyBindingResult(request, "password");
        validator.validate(request, errors);
        Optional<ObjectError> error = findErrorCode(errors, "password needs at least one special symbol");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    //search through a dictionary of words or common passwords
    public void shouldRejectPasswordIfItsTooCommon(){

    }

    @Test
    public void shouldRejectUsernameIfAlreadyExists(){
        when(userRepository.findOne(any())).thenReturn(Optional.of(new User()));
        CreateUserRequest request = CreateUserRequest.builder()
                .username("usernameThatAlreadyExists")
                .build();

        Errors errors = new BeanPropertyBindingResult(request, "password");
        validator.validate(request, errors);
        Optional<ObjectError> error = findErrorCode(errors, "username already exists");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectPasswordIfItDoesNotMatch(){
        CreateUserRequest request = CreateUserRequest.builder()
                .username("validUsername")
                .password("12345678")
                .matchPassword("abcdefgh")
                .build();

        Errors errors = new BeanPropertyBindingResult(request, "password");
        validator.validate(request, errors);
        Optional<ObjectError> error = findErrorCode(errors, "password does not not match");
        assertThat(error.isPresent(), is(true));
    }

    @Test
    public void shouldRejectEmailIfAlreadyExists(){

    }

    @Test
    public void shouldRejectEmailIfDoesNotMatchEmailPattern(){

    }
}