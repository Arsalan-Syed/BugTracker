package com.github.syed.bugtracker.integration.steps;

import com.github.syed.bugtracker.user.CreateUserRequest;
import com.github.syed.bugtracker.user.User;
import com.github.syed.bugtracker.user.UserRepository;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserSteps {

    @Autowired
    UserRepository repository;

    @Given("^CreateUserRequest \"([^\"]*)\"$")
    public void createUserRequest(String requestId, DataTable dataTable) throws NoSuchFieldException, IllegalAccessException {
        List<Map<String, String>> listOfMaps = dataTable.asMaps(String.class, String.class);

        for(Map<String, String> map : listOfMaps){
            CreateUserRequest request = new CreateUserRequest();
            DataStorage.setFields(map, request);
            DataStorage.put(requestId, request);
        }
    }

    @And("^I can retrieve the user \"([^\"]*)\" from the database$")
    public void iCanRetrieveTheUserFromTheDatabase(String username) {
        Optional<User> userOptional = repository.findOne(Example.of(
                User.builder()
                    .username(username)
                    .build()
        ));
        assertThat(userOptional.isPresent(), is(true));
    }
}
