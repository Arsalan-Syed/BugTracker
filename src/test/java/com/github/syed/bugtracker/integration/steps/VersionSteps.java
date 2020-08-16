package com.github.syed.bugtracker.integration.steps;

import com.github.syed.bugtracker.integration.AbstractCucumberSteps;
import cucumber.api.java.en.Then;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VersionSteps extends AbstractCucumberSteps {

    @Then("^the client receives a version of \"([^\"]*)\"$")
    public void theClientReceivesAVersionOf(String expectedVersion) {
        String response = RestSteps.response.body();
        assertThat(response, is(expectedVersion));
    }
}