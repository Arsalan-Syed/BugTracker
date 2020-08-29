package com.github.syed.bugtracker.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public class Name {
    @NotEmpty
    private String firstName;

    private String middleName;

    @NotEmpty
    private String lastName;
}
