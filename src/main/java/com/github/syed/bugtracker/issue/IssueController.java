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

    @PostMapping(value = "/project/{projectName}/issue")
    public void createIssue(@Valid @RequestBody Issue issue, @PathVariable String projectName) {
        issueService.createIssue(issue, projectName);
    }

    @GetMapping("/project/{projectName}/issues")
    public List<Issue> getIssues(@PathVariable String projectName) {
        return issueService.getIssues(projectName);
    }

    //TODO why do we need the project name?
    @DeleteMapping(value = "/project/{projectName}/issue/{issueName}")
    public void deleteIssue(String projectName, String issueName) {
        issueService.deleteIssue(issueName);
    }
}
