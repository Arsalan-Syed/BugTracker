package com.github.syed.bugtracker.user;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CreateUserRequest {
    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    private String matchPassword;

    @NotNull
    @NotEmpty
    private String email;

    @Valid
    private Name name;
}
