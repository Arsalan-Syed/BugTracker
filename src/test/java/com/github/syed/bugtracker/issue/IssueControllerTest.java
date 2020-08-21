package com.github.syed.bugtracker.issue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class IssueControllerTest {

    @Mock
    IssueService issueService;

    IssueController issueController;

    @Before
    public void setup(){
         issueController = new IssueController(issueService);
    }

    @Test
    public void shouldCreateIssue(){
        CreateIssueRequest request = CreateIssueRequest.builder()
                .issue(new Issue())
                .projectName("Project name")
                .build();
        issueController.createIssue(request);
        verify(issueService, times(1)).createIssue(request.getIssue(), request.getProjectName());
    }

}