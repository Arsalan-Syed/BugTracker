package com.github.syed.bugtracker.project;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
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

    @DeleteMapping(value = "/project/{name}")
    public void deleteProject(@PathVariable String name) {
        projectService.deleteProject(name);
    }

}