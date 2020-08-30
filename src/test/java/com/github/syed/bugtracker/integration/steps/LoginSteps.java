package com.github.syed.bugtracker.integration.steps;

import cucumber.api.java.en.And;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class LoginSteps {

    @And("^the client should receive a JWT token$")
    public void theClientShouldReceiveAJWTToken() {
        String responseBody = RestSteps.response.body();
        assertThat(responseBody, not(nullValue())); //TODO make condition stronger
    }
}
