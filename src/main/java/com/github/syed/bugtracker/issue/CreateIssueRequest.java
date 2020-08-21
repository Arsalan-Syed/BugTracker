package com.github.syed.bugtracker.issue;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateIssueRequest {
    Issue issue;
    String projectName;
}