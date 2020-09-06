package com.github.syed.bugtracker.user;

import com.github.syed.bugtracker.CreateUserRequestValidator;
import com.github.syed.bugtracker.auth.LoginRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {

    @Mock
    CreateUserRequestValidator validator;

    @Mock
    UserService service;

    AuthenticationController controller;

    @Before
    public void setup(){
        controller = new AuthenticationController(validator, service);
    }

    @Test
    public void shouldCallServiceWhenRegisterUser(){
        CreateUserRequest request = CreateUserRequest.builder().build();
        controller.createUser(request);
        verify(service, times(1)).createUser(any());
    }

    @Test
    public void shouldCallServiceWhenLogin() throws Exception {
        LoginRequest loginRequest = LoginRequest.builder().build();
        controller.login(loginRequest);
        verify(service, times(1)).login(any());
    }

}