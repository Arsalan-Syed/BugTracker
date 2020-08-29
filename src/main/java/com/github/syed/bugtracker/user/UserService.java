package com.github.syed.bugtracker.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(CreateUserRequest request) {
        userRepository.save(User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .name(request.getName())
                .build());
    }

}
