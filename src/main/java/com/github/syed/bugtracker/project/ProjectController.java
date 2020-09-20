package com.github.syed.bugtracker.project;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping(value = "/project")
    public Project createProject(@Valid @RequestBody Project project){
        return projectService.create(project);
    }

    @GetMapping(value = "/projects")
    public List<Project> getProjects(){
        return projectService.getProjects();
    }

    @GetMapping(value = "/projects/{name}")
    public Project getProject(@PathVariable String name) throws MissingProjectException {
        return projectService.getProject(name);
    }

    @DeleteMapping(value = "/project/{name}")
    public void deleteProject(@PathVariable String name) {
        projectService.deleteProject(name);
    }

}