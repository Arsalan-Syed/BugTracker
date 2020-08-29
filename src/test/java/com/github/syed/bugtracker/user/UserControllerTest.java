package com.github.syed.bugtracker.user;

import com.github.syed.bugtracker.CreateUserRequestValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    CreateUserRequestValidator validator;

    @Mock
    UserService service;

    UserController controller;

    @Before
    public void setup(){
        controller = new UserController(validator, service);
    }

    @Test
    public void shouldCallService(){
        CreateUserRequest request = CreateUserRequest.builder().build();
        controller.createUser(request);
        verify(service, times(1)).createUser(any());
    }

}