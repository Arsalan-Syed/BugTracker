package com.github.syed.bugtracker.user;

import com.github.syed.bugtracker.auth.InvalidCredentialsException;
import com.github.syed.bugtracker.auth.JwtTokenUtil;
import com.github.syed.bugtracker.auth.LoginRequest;
import com.github.syed.bugtracker.auth.LoginResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtTokenUtil jwtTokenUtil;

    @Mock
    AuthenticationManager authenticationManager;

    UserService userService;

    @Before
    public void setup(){
        userService = new UserService(userRepository, passwordEncoder, jwtTokenUtil, authenticationManager);
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
        when(userRepository.findByUsername(anyString())).thenReturn(java.util.Optional.of(new User()));
        userService.createUser(request);
        verify(userRepository, times(1)).save(any());
    }

    @Test //handle this another way later
    public void shouldAssignUserDeveloperRoleByDefault(){
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

        when(userRepository.findByUsername(anyString())).thenReturn(java.util.Optional.of(new User()));
        userService.createUser(request);

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(argumentCaptor.capture());
        User user = argumentCaptor.getValue();

        assertThat(user.getRole(), is(Role.DEV));
    }

    @Test
    public void shouldLoginUserIfUsernameAndPasswordCorrect() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .username("username")
                .password("password")
                .build();

        when(userRepository.findByUsername(anyString())).thenReturn(java.util.Optional.of(new User()));
        when(jwtTokenUtil.generateJwtToken(any())).thenReturn("Token");
        LoginResponse response = userService.login(request);
        assertThat(response.getAuthToken(), not(nullValue()));
    }

    @Test(expected = InvalidCredentialsException.class)
    public void shouldRejectLoginIfUsernameOrPasswordIncorrect() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .username("username")
                .password("password")
                .build();

        when(authenticationManager.authenticate(any())).thenThrow(BadCredentialsException.class);
        userService.login(request);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldThrowExceptionIfUsernameNotFound(){
        userService.loadUserByUsername("username");
    }
}