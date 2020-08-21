package com.github.syed.bugtracker.issue;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping(value = "/issue")
    public void createIssue(@Valid @RequestBody CreateIssueRequest request) {
        issueService.createIssue(request.getIssue(), request.getProjectName());
    }
}
