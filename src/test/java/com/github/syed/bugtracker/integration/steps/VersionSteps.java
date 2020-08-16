package com.github.syed.bugtracker.integration.steps;

import com.github.syed.bugtracker.integration.AbstractCucumberSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VersionSteps extends AbstractCucumberSteps {

    String response;

    @When("^the client calls GET to \"([^\"]*)\"$")
    public void theClientCallsGETTo(String path) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080"+path))
                .timeout(Duration.ofMinutes(1))
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    @Then("^the client receives a version of \"([^\"]*)\"$")
    public void theClientReceivesAVersionOf(String expectedVersion) {
        assertThat(response, is(expectedVersion));
    }
}
