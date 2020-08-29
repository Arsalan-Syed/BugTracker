package com.github.syed.bugtracker.user;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CreateUserRequest {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String matchPassword;

    @NotEmpty
    private String email;

    @Valid
    private Name name;
}
