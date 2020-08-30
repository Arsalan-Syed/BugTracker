package com.github.syed.bugtracker.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    UserService userService;

    @Before
    public void setup(){
        userService = new UserService(userRepository, passwordEncoder);
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

    @Test
    public void shouldLoginUserIfUsernameAndPasswordCorrect() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .username("username")
                .password("password")
                .build();

        when(userRepository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(java.util.Optional.of(new User()));

        userService.login(request);
        assertThat(true, is(false));
    }


    @Test
    public void shouldRejectLoginIfUsernameOrPasswordIncorrect() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .username("username")
                .password("password")
                .build();

        when(userRepository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.empty());

        userService.login(request);
        assertThat(true, is(false));
    }
}