package com.github.syed.bugtracker.issue;

import com.github.syed.bugtracker.project.Project;
import com.github.syed.bugtracker.project.ProjectRepository;
import com.github.syed.bugtracker.project.exception.ProjectNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

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
        Optional<Project> optionalProject = findProjectByName(projectName);

        if(optionalProject.isEmpty()){
            throw new ProjectNotFoundException("Could not find project with name "+ projectName);
        }

        issue.setProject(optionalProject.get());
        repository.save(issue);
    }

    private Optional<Project> findProjectByName(String projectName) {
        return projectRepository.findOne(Example.of(
                    Project.builder().name(projectName).build()
            ));
    }
}
