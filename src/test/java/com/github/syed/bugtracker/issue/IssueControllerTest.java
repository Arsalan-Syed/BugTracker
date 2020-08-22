package com.github.syed.bugtracker.issue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
        issueController.createIssue(new Issue(), "Project name");
        verify(issueService, times(1)).createIssue(any(), anyString());
    }

    @Test
    public void shouldGetAllIssues(){
        String projectName = "Project Name";
        issueController.getIssues(projectName);
        verify(issueService, times(1)).getIssues(projectName);
    }

}