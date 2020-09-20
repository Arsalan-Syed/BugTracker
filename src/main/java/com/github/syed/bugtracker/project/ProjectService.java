package com.github.syed.bugtracker.project;

import com.github.syed.bugtracker.project.exception.DuplicateProjectNameException;
import com.github.syed.bugtracker.project.exception.ProjectNotFoundException;
import com.github.syed.bugtracker.user.User;
import com.github.syed.bugtracker.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final UserService userService;
    private final ProjectDAO dao;

    public ProjectService(UserService userService, ProjectDAO dao) {
        this.userService = userService;
        this.dao = dao;
    }

    public Project create(Project project) {
        validateProjectNameUnique(project.getName());
        User user = userService.fetchCurrentUser();
        project.setUser(user);
        return repository.save(project);
    }

    public List<Project> getProjects() {
        User user = userService.fetchCurrentUser();
        return dao.getAllProjects(user);
    }

    private void validateProjectNameUnique(String projectName) {
        User user = userService.fetchCurrentUser();
        Optional<Project> queriedProject = dao.findByUserAndName(user, projectName);

        if(queriedProject.isPresent()){
            String message = String.format("Name %s already exists in database", projectName);
            throw new DuplicateProjectNameException(message);
        }
    }

    public void deleteProject(String projectName) {
        User user = userService.fetchCurrentUser();
        Optional<Project> project = dao.findByUserAndName(user, projectName);

        if(project.isEmpty()) {
            throw new ProjectNotFoundException("Could not find project with name "+projectName);
        }
        dao.delete(project.get());
    }

    public Project getProject(String projectName) throws MissingProjectException {
        User user = userService.fetchCurrentUser();
        Optional<Project> project = dao.findByUserAndName(user, projectName);

        if(project.isEmpty()){
            throw new MissingProjectException("Could not find project with name "+projectName);
        }

        return project.get();
    }
}
