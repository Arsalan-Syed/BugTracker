package com.github.syed.bugtracker.integration.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syed.bugtracker.issue.Issue;
import com.github.syed.bugtracker.issue.IssueRepository;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

public class IssueSteps {

    @Autowired
    IssueRepository repository;

    @Given("^Issue \"([^\"]*)\"$")
    public void issue(String issueId, DataTable dataTable) throws NoSuchFieldException, IllegalAccessException {
        List<Map<String, String>> listOfMaps = dataTable.asMaps(String.class, String.class);

        for(Map<String, String> map : listOfMaps){
            Issue issue = new Issue();
            DataStorage.setFields(map, issue);
            DataStorage.put(issueId, issue);
        }
    }

    @And("^I can retrieve Issue \"([^\"]*)\" from the database$")
    public void iCanRetrieveIssueFromTheDatabase(String issueId) throws Throwable {
        Issue expectedIssue = (Issue) DataStorage.get(issueId);
        Optional<Issue> savedIssue = repository.findOne(Example.of(
                Issue.builder()
                        .name(expectedIssue.getName())
                        .build()
        ));
        if(savedIssue.isPresent()){
            DataStorage.put("DB-"+issueId, savedIssue.get());
        } else {
            throw new Exception();
        }
    }

    @And("^the Issue \"([^\"]*)\" matches exactly$")
    public void theIssueMatchesExactly(String issueId) throws IllegalAccessException {
        Issue expectedIssue = (Issue) DataStorage.get(issueId);
        Issue actualIssue = (Issue) DataStorage.get("DB-"+issueId);
        matchIssues(expectedIssue, actualIssue);
    }

    @And("^the response contains issues$")
    public void theResponseContainsIssues(List<String> issueIds) throws JsonProcessingException, IllegalAccessException {
        String responseBody = RestSteps.response.body();
        List<Issue> issues = new ObjectMapper().readValue(responseBody, new TypeReference<>(){});

        for(String id : issueIds){
            Issue expectedIssue = (Issue) DataStorage.get(id);
            Issue actualIssue = issues.stream()
                    .filter(project -> expectedIssue.getName().equals(project.getName()))
                    .findFirst()
                    .get();
            matchIssues(expectedIssue, actualIssue);
        }
    }

    @And("^the response contains no issues$")
    public void theResponseContainsNoIssues() throws JsonProcessingException {
        String responseBody = RestSteps.response.body();
        List<Issue> issues = new ObjectMapper().readValue(responseBody, new TypeReference<>(){});
        assertThat(issues, empty());
    }

    //TODO generic matching algorithm that recursively checks objects
    private void matchIssues(Issue expectedIssue, Issue actualIssue) throws IllegalAccessException {
        Field[] issueFields = expectedIssue.getClass().getDeclaredFields();

        Set<String> ignoredFields = Set.of("id", "project", "status");

        for(Field field : issueFields){
            if(ignoredFields.contains(field.getName())){
                continue;
            }

            field.setAccessible(true);
            Object expectedIssueValue = field.get(expectedIssue);
            Object actualIssueValue = field.get(actualIssue);
            assertThat(actualIssueValue, is(expectedIssueValue));
            field.setAccessible(false);
        }
    }

    @And("^the Issue \"([^\"]*)\" is no longer in the database$")
    public void theIssueIsNoLongerInTheDatabase(String issueId) throws Throwable {
        Issue issue = (Issue) DataStorage.get(issueId);

        repository.findOne(Example.of(
                Issue.builder().name(issue.getName()).build()
        ));
    }
}