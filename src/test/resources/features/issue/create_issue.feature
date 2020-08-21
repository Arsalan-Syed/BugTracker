Feature: The client can create an issue

  Background:
    Given Project "defaultProject"
      | name         | color   |
      | Project Name | #0000ff |
    When the client calls POST to "/project" with "defaultProject"
    Then the client should receive a status code of 200

  Scenario: Create an issue
    Given Issue "defaultIssue"
      | name        |
      | Issue Name  |
    When the client calls POST to "/issue" with "defaultIssue" and parameter "Project Name"
    Then the client should receive a status code of 200
    And I can retrieve Issue "defaultIssue" from the database
    And the Issue "defaultIssue" matches exactly