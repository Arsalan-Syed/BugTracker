package com.github.syed.bugtracker.project;

import com.github.syed.bugtracker.project.exception.DuplicateProjectNameException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public Project create(Project project) {
        validateProjectNameUnique(project.getName());
        return repository.save(project);
    }

    private void validateProjectNameUnique(String name) {
        Optional<Project> queriedProject = repository.findOne(
                Example.of(Project.builder().name(name).build())
        );

        if(queriedProject.isPresent()){
            String message = String.format("Name %s already exists in database", name);
            throw new DuplicateProjectNameException(message);
        }
    }

}
