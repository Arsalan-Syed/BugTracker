package com.github.syed.bugtracker.project;

import com.github.syed.bugtracker.project.exception.DuplicateProjectNameException;
import com.github.syed.bugtracker.project.exception.ProjectNotFoundException;
import com.github.syed.bugtracker.user.User;
import com.github.syed.bugtracker.user.UserService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final UserService userService;

    public ProjectService(ProjectRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public Project create(Project project) {
        validateProjectNameUnique(project.getName());
        User user = userService.fetchCurrentUser();
        project.setUser(user);
        return repository.save(project);
    }

    public List<Project> getProjects() {
        User user = userService.fetchCurrentUser();
        return repository.findAllByUser(user);
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

    public void deleteProject(String projectName) {
        Optional<Project> project = repository.findOne(Example.of(
                Project.builder()
                        .name(projectName)
                        .build()
        ));

        if(project.isEmpty()) {
            throw new ProjectNotFoundException("Could not find project with name "+projectName);
        }
        repository.delete(project.get());
    }
}
