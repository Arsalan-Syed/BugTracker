package com.github.syed.bugtracker.issue;

import com.github.syed.bugtracker.project.Project;
import com.github.syed.bugtracker.project.ProjectRepository;
import com.github.syed.bugtracker.project.exception.ProjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IssueServiceTest {

    @Mock
    IssueRepository issueRepository;

    @Mock
    ProjectRepository projectRepository;

    IssueService issueService;

    @Before
    public void setup(){
        issueService = new IssueService(issueRepository, projectRepository);
    }

    @Test
    public void shouldCreateIssue(){
        Issue issue = Issue.builder().build();
        String projectName = "Project name";

        when(projectRepository.findOne(any())).thenReturn(Optional.of(Project.builder().name(projectName).build()));
        issueService.createIssue(issue, projectName);
        verify(issueRepository, times(1)).save(issue);
    }

    @Test(expected = ProjectNotFoundException.class)
    public void shouldRejectCreatingIssueIfProjectDoesNotExist(){
        Issue issue = Issue.builder().build();
        String projectName = "Unknown Project name";

        when(projectRepository.findOne(any())).thenReturn(Optional.empty());
        issueService.createIssue(issue, projectName);
    }

}