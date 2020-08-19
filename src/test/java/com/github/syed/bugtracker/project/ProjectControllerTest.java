package com.github.syed.bugtracker.project;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProjectControllerTest {

    @Mock
    ProjectService projectService;

    ProjectController projectController;

    @Before
    public void setup(){
        projectController = new ProjectController(projectService);
    }

    @Test
    public void shouldCallServiceForCreatingAProject(){
        Project project = new Project();
        projectController.createProject(project);
        verify(projectService, times(1)).create(any(Project.class));
    }

    @Test
    public void shouldCallServiceForGettingAllProjects(){
        projectController.getProjects();
        verify(projectService, times(1)).getProjects();
    }

    @Test
    public void shouldCallServiceForDeletingAProject(){
        String projectName = "name";
        projectController.deleteProject(projectName);
        verify(projectService, times(1)).deleteProject(projectName);
    }
}