package com.github.syed.bugtracker.project;

import com.github.syed.bugtracker.AbstractIntegrationTest;
import com.github.syed.bugtracker.issue.Issue;
import com.github.syed.bugtracker.issue.IssueRepository;
import com.github.syed.bugtracker.issue.Priority;
import com.github.syed.bugtracker.issue.Status;
import com.github.syed.bugtracker.user.Name;
import com.github.syed.bugtracker.user.Role;
import com.github.syed.bugtracker.user.User;
import com.github.syed.bugtracker.user.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class ProjectDAOIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    ProjectDAO dao;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldFetchIssuesForProject(){
        User user = createUser();
        Project project = createProject(user);

        Issue issue = createIssue(project);
        Issue issue2 = createIssue(project);

        Set<Issue> issues = new HashSet<>();
        issues.add(issue);
        issues.add(issue2);

        project.setIssues(issues);

        projectRepository.save(project);

        List<Project> projects = dao.getAllProjects(user);
        assertThat(projects.get(0).getIssues(), hasSize(2));
    }

    @Test
    public void shouldOnlyFetchProjectForUser(){
        User user = createUser();
        Project project = createProject(user);
        project.setIssues(Collections.emptySet());

        User user2 = createUser();
        Project project2 = createProject(user2);
        project.setIssues(Collections.emptySet());

        projectRepository.save(project);
        projectRepository.save(project2);

        List<Project> projects = dao.getAllProjects(user);
        assertThat(projects, hasSize(1));
    }

    private Issue createIssue(Project project) {
        return Issue.builder()
                .project(project)
                .issueId("1234")
                .name("Issue name")
                .priority(Priority.LOW)
                .status(Status.TODO)
                .build();
    }

    private Project createProject(User user) {
        Project project = Project.builder()
                .user(user)
                .name("Name")
                .color(Color.WHITE)
                .build();
        projectRepository.save(project);
        return project;
    }

    private User createUser() {
        User user = User.builder().username("test").password("password").role(Role.DEV).email("email").name(Name.builder().firstName("dasdfas").lastName("da").build()).build();
        userRepository.save(user);
        return user;
    }

}