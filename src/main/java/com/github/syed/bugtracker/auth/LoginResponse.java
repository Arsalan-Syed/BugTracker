package com.github.syed.bugtracker.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private final String authToken;
}
