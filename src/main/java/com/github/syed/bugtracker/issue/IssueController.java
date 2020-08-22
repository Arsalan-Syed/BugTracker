package com.github.syed.bugtracker.issue;


import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    //why not post an issue object to project/{projectName}/issue
    @PostMapping(value = "/issue")
    public void createIssue(@Valid @RequestBody CreateIssueRequest request) {
        issueService.createIssue(request.getIssue(), request.getProjectName());
    }

    @GetMapping("/project/{projectName}/issues")
    public List<Issue> getIssues(@PathVariable String projectName) {
        return issueService.getIssues(projectName);
    }
}
