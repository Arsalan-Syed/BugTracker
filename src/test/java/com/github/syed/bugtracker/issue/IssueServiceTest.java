package com.github.syed.bugtracker.issue;

import com.github.syed.bugtracker.issue.exception.IssueNotFoundException;
import com.github.syed.bugtracker.project.Project;
import com.github.syed.bugtracker.project.ProjectRepository;
import com.github.syed.bugtracker.project.exception.ProjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.github.syed.bugtracker.issue.Status.TODO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
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

    @Test
    public void shouldSetStatusOfTodoWhenCreatingNewIssue(){
        Issue issue = Issue.builder().build();
        String projectName = "Project name";

        when(projectRepository.findOne(any())).thenReturn(Optional.of(Project.builder().name(projectName).build()));

        issueService.createIssue(issue, projectName);

        Issue capturedIssue = captureIssue();
        assertThat(capturedIssue.getStatus(), is(TODO));
    }

    @Test(expected = ProjectNotFoundException.class)
    public void shouldRejectCreatingIssueIfProjectDoesNotExist(){
        Issue issue = Issue.builder().build();
        String projectName = "Unknown Project name";

        when(projectRepository.findOne(any())).thenReturn(Optional.empty());
        issueService.createIssue(issue, projectName);
    }

    @Test
    public void shouldGetAllIssuesForProject(){
        String projectName = "Project name";

        when(projectRepository.findOne(any())).thenReturn(Optional.of(Project.builder().name(projectName).build()));
        when(issueRepository.findByProject(any())).thenReturn(List.of(new Issue()));
        List<Issue> issues = issueService.getIssues(projectName);
        assertThat(issues, hasSize(1));
    }

    @Test
    public void shouldDeleteIssue(){
        String issueName = "Issue Name";
        when(issueRepository.findOne(any())).thenReturn(Optional.of(new Issue()));
        issueService.deleteIssue(issueName);
        verify(issueRepository, times(1)).delete(any());
    }

    @Test(expected = IssueNotFoundException.class)
    public void shouldThrowExceptionIfIssueDoesNotExist(){
        String issueName = "Issue Name";
        when(issueRepository.findOne(any())).thenReturn(Optional.empty());
        issueService.deleteIssue(issueName);
    }

    private Issue captureIssue() {
        ArgumentCaptor<Issue> captor = ArgumentCaptor.forClass(Issue.class);
        verify(issueRepository).save(captor.capture());
        return captor.getValue();
    }

}