package com.github.syed.bugtracker.integration.steps;

import com.github.syed.bugtracker.project.Project;
import com.github.syed.bugtracker.project.ProjectRepository;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ProjectSteps {

    @Autowired
    ProjectRepository repository;

    @Given("^Project \"([^\"]*)\"$")
    public void project(String projectId, DataTable dataTable) throws NoSuchFieldException, IllegalAccessException {
        List<Map<String, String>> listOfMaps = dataTable.asMaps(String.class, String.class);

        for(Map<String, String> map : listOfMaps){
            Project project = new Project();
            setProjectFields(map, project);
            DataStorage.put(projectId, project);
        }
    }

    @And("^I can retrieve Project \"([^\"]*)\" from the database$")
    public void iCanRetrieveProjectFromTheDatabase(String projectId) throws Exception {
        Project expectedProject = (Project) DataStorage.get(projectId);
        Optional<Project> savedProject = repository.findOne(Example.of(
                Project.builder()
                        .name(expectedProject.getName())
                        .color(expectedProject.getColor())
                        .build()
        ));
        if(savedProject.isPresent()){
            DataStorage.put("DB-"+projectId, savedProject.get());
        } else {
            throw new Exception();
        }
    }

    @And("^the Project \"([^\"]*)\" matches exactly$")
    public void theProjectMatchesExactly(String projectId){
        Project expectedProject = (Project) DataStorage.get(projectId);
        Project actualProject = (Project) DataStorage.get("DB-"+projectId);

        assertThat(actualProject.getName(), is(expectedProject.getName()));
        assertThat(actualProject.getColor(), is(expectedProject.getColor()));
    }

    private void setProjectFields(Map<String, String> map, Project project) throws NoSuchFieldException, IllegalAccessException {
        for(String key : map.keySet()){
            Class<?> c = project.getClass();
            Field field = c.getDeclaredField(key);
            field.setAccessible(true);

            if(field.getType() == Color.class){
                Color color = convertHexStringToColor(map, key);
                field.set(project, color);
            } else{
                field.set(project, map.get(key));
            }

            field.setAccessible(false);
        }
    }

    private Color convertHexStringToColor(Map<String, String> map, String key) {
        String colorStr = map.get(key);
        return new Color(
                Integer.valueOf(colorStr.substring(1,3), 16),
                Integer.valueOf(colorStr.substring(3,5), 16),
                Integer.valueOf(colorStr.substring(5,7), 16)
        );
    }

}
