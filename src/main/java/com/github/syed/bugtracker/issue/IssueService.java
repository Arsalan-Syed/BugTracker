package com.github.syed.bugtracker.issue;

import com.github.syed.bugtracker.issue.exception.InvalidIssueStatusException;
import com.github.syed.bugtracker.issue.exception.InvalidRoleException;
import com.github.syed.bugtracker.issue.exception.IssueNotFoundException;
import com.github.syed.bugtracker.project.Project;
import com.github.syed.bugtracker.project.ProjectRepository;
import com.github.syed.bugtracker.project.exception.ProjectNotFoundException;
import com.github.syed.bugtracker.user.Role;
import com.github.syed.bugtracker.user.User;
import com.github.syed.bugtracker.user.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.github.syed.bugtracker.issue.Status.TODO;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public IssueService(IssueRepository issueRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.issueRepository = issueRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Issue createIssue(Issue issue, String projectName) {
        Project project = findProjectByName(projectName);

        issue.setStatus(TODO);
        issue.setIssueId(generateRandomString());
        issue.setProject(project);

        Set<Issue> issues = project.getIssues();
        if(issues == null){
            issues = new HashSet<>();
        }
        issues.add(issue);
        project.setIssues(issues);

        projectRepository.save(project);
        return issue;
    }

    public Set<Issue> getIssues(String projectName) {
        Project project = findProjectByName(projectName);
        return issueRepository.findByProject(project);
    }

    private Project findProjectByName(String projectName) {
        Optional<Project> optionalProject = projectRepository.findOne(Example.of(
                Project.builder().name(projectName).build()
        ));

        if(optionalProject.isEmpty()){
            throw new ProjectNotFoundException("Could not find project with name "+ projectName);
        }

        return optionalProject.get();
    }

    public void deleteIssue(String issueName){
        Optional<Issue> issue = issueRepository.findOne(Example.of(
                Issue.builder()
                    .name(issueName)
                    .build())
        );

        if(issue.isEmpty()){
            throw new IssueNotFoundException("Could not find issue with name "+issueName);
        }

        issueRepository.delete(issue.get());
    }

    public void assignDeveloper(AssignDeveloperRequest request) {
        Optional<Issue> issueOptional = issueRepository.findByIssueId(request.getIssueId());
        if(issueOptional.isEmpty()){
            throw new IssueNotFoundException("Could not find issue with id: "+request.getIssueId());
        }

        if(issueOptional.get().getStatus() != TODO){
            throw new InvalidIssueStatusException("Issue should have a status of TODO to be assigned a developer");
        }

        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());

        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("Could not find username: "+request.getUsername());
        }

        if(userOptional.get().getRole() != Role.DEV){
            throw new InvalidRoleException("Username "+request.getUsername()+" does not have a developer role");
        }



        Issue issue = issueOptional.get();
        issue.setAssignedUser(userOptional.get());

        issueRepository.save(issue);
    }

    private String generateRandomString() {
        int length = 8;
        boolean useLetters = false;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

}