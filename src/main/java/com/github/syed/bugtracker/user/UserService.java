package com.github.syed.bugtracker.user;

import com.github.syed.bugtracker.auth.InvalidCredentialsException;
import com.github.syed.bugtracker.auth.JwtTokenUtil;
import com.github.syed.bugtracker.auth.LoginRequest;
import com.github.syed.bugtracker.auth.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse createUser(CreateUserRequest request) {
        userRepository.save(User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .name(request.getName())
                .role(Role.DEV)
                .build());

        UserDetails userDetails = loadUserByUsername(request.getUsername());
        return LoginResponse.builder()
                .authToken(jwtTokenUtil.generateJwtToken(userDetails))
                .build();
    }

    public LoginResponse login(LoginRequest loginRequest) {
        authenticate(loginRequest);
        UserDetails userDetails = loadUserByUsername(loginRequest.getUsername());
        return LoginResponse.builder()
                .authToken(jwtTokenUtil.generateJwtToken(userDetails))
                .build();
    }

    private void authenticate(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException(username);
        }

        return user.get();
    }

    public User fetchCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User) principal;
    }
}
