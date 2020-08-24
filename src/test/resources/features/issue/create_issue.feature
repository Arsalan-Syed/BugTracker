Feature: The client can create an issue

  Scenario: Create an issue
    Given Project "defaultProject"
      | name         | color   |
      | Project Name | #0000ff |
    And Issue "defaultIssue"
      | name        | priority |
      | Issue Name  | LOW      |
    And the client calls POST to "/project" with "defaultProject"
    And the client should receive a status code of 200

    When the client calls POST to "/project/Project%20Name/issue" with "defaultIssue"
    Then the client should receive a status code of 200
    And I can retrieve Issue "defaultIssue" from the database
    And the Issue "defaultIssue" matches exactly


  Scenario: Reject creating an issue if the project name doesn't exist
    Given Issue "defaultIssue"
      | name        | priority |
      | Issue Name  | LOW      |
    When the client calls POST to "/project/Project%20Name/issue" with "defaultIssue"
    Then the client should receive a status code of 404