package com.github.syed.bugtracker.issue;

import com.github.syed.bugtracker.project.Project;
import com.github.syed.bugtracker.project.ProjectRepository;
import com.github.syed.bugtracker.project.exception.ProjectNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    private final IssueRepository repository;
    private final ProjectRepository projectRepository;

    public IssueService(IssueRepository repository, ProjectRepository projectRepository) {
        this.repository = repository;
        this.projectRepository = projectRepository;
    }

    public void createIssue(Issue issue, String projectName) {
        Project project = findProjectByName(projectName);
        issue.setProject(project);
        repository.save(issue);
    }

    public List<Issue> getIssues(String projectName) {
        Project project = findProjectByName(projectName);
        return repository.findByProject(project);
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

}