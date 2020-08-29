package com.github.syed.bugtracker.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    UserService userService;

    @Before
    public void setup(){
        userService = new UserService(userRepository);
    }

    @Test
    public void shouldSaveUserIfSuccessfullyCreated(){
        CreateUserRequest request = CreateUserRequest.builder()
                .username("username")
                .password("password")
                .matchPassword("password")
                .email("email@domain.com")
                .name(Name.builder().firstName("firstName")
                        .middleName("middleName")
                        .lastName("lastName").build()
                )
                .build();

        userService.createUser(request);
        verify(userRepository, times(1)).save(any());
    }

}