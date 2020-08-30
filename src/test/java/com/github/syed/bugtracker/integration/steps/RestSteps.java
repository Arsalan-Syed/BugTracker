package com.github.syed.bugtracker.integration.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.syed.bugtracker.integration.DataStorage;
import com.github.syed.bugtracker.issue.CreateIssueRequest;
import com.github.syed.bugtracker.issue.Issue;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RestSteps {

    static HttpResponse<String> response;

    @When("^the client calls GET to \"([^\"]*)\"$")
    public void theClientCallsGETTo(String path) throws IOException, InterruptedException {
        HttpClient client = createHttpClient();
        HttpRequest request = createGetRequest(path);
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @When("^the client calls POST to \"([^\"]*)\" with body$")
    public void theClientCallsPOSTToWithBody(String path, String body) throws Throwable {
        HttpClient client = createHttpClient();
        HttpRequest request = createPostRequest(path, body);
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @When("^the client calls POST to \"([^\"]*)\" with \"([^\"]*)\"$")
    public void theClientCallsPOSTToWith(String path, String objectId) throws Throwable {
        Object object = DataStorage.get(objectId);
        String body = new ObjectMapper().writeValueAsString(object);
        theClientCallsPOSTToWithBody(path, body);
    }

    @When("^the client calls POST to \"([^\"]*)\" with \"([^\"]*)\" and parameter \"([^\"]*)\"$")
    public void theClientCallsPOSTToWithAndParameter(String path, String objectId, String projectName) throws Throwable {
        Issue issue = (Issue) DataStorage.get(objectId);
        CreateIssueRequest request = CreateIssueRequest.builder()
                .issue(issue)
                .projectName(projectName)
                .build();

        String body = new ObjectMapper().writeValueAsString(request);
        theClientCallsPOSTToWithBody(path, body);
    }

    @When("^the client calls DELETE to \"([^\"]*)\"$")
    public void theClientCallsDELETEToWithString(String path) throws Throwable {
        HttpClient client = createHttpClient();
        HttpRequest request = createDeleteRequest(path);
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Then("^the client should receive a status code of (\\d+)$")
    public void theClientShouldReceiveAStatusCodeOf(int expectedStatusCode) {
        assertThat(response.statusCode(), is(expectedStatusCode));
    }

    private HttpClient createHttpClient() {
        return HttpClient.newBuilder()
                .build();
    }

    private HttpRequest createPostRequest(String path, String body) {
        return createHttpRequestBuilder(path)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
    }

    private HttpRequest createGetRequest(String path) {
        return createHttpRequestBuilder(path).GET()
                .build();
    }

    private HttpRequest createDeleteRequest(String path) {
        return HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080" + path))
                .timeout(Duration.ofMinutes(1))
                .DELETE()
                .build();
    }

    private HttpRequest.Builder createHttpRequestBuilder(String path) {
        return HttpRequest.newBuilder()
                .header("content-type","application/json")
                .uri(URI.create("http://localhost:8080" + path))
                .timeout(Duration.ofMinutes(1));
    }

}
