package com.github.syed.bugtracker.issue;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
public class AssignDeveloperRequest {
    @NotEmpty
    private String username;

    @NotEmpty
    private String issueId;
}
