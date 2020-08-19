package com.github.syed.bugtracker.project;

import com.github.syed.bugtracker.project.exception.DuplicateProjectNameException;
import com.github.syed.bugtracker.project.exception.ProjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {

    @Mock
    ProjectRepository repository;

    ProjectService service;

    @Before
    public void setup(){
        service = new ProjectService(repository);
    }

    @Test
    public void shouldCreateNewProject(){
        Project project = new Project();
        service.create(project);
        verify(repository, times(1)).save(any(Project.class));
    }

    @Test(expected = DuplicateProjectNameException.class)
    public void shouldNotCreateProjectIfThereExistsProjectWithSameName(){
        when(repository.findOne(any())).thenReturn(Optional.of(Project.builder()
                .name("Name")
                .build()));

        Project project = Project.builder()
                .name("Name")
                .build();

        service.create(project);
    }

    @Test
    public void shouldGetAllProjects(){
        when(repository.findAll()).thenReturn(List.of(
           Project.builder().build()
        ));

        List<Project> projects = service.getProjects();
        assertThat(projects, hasSize(1));
    }

    @Test
    public void shouldDeleteAProject(){
        String projectName = "projectName";
        Project project = Project.builder().name(projectName).build();
        when(repository.findOne(any())).thenReturn(Optional.ofNullable(project));
        service.deleteProject(projectName);
        verify(repository, times(1)).delete(any());
    }

    @Test(expected = ProjectNotFoundException.class)
    public void shouldThrowExceptionIfCantFindProject(){
        service.deleteProject("projectName");
    }
}