Feature: The client can delete an issue

  Scenario: Should delete an issue
    Given Project "defaultProject"
      | name         | color   |
      | Project Name | #0000ff |
    And Issue "defaultIssue"
      | name        | priority |
      | Issue Name  | LOW      |
    And the client calls POST to "/project" with "defaultProject"
    And the client calls POST to "/project/Project%20Name/issue" with "defaultIssue"
    When the client calls DELETE to "/project/Project%20Name/issue/Issue%20Name"
    Then the client should receive a status code of 200
    And the Issue "defaultIssue" is no longer in the database


  Scenario: Should reject deleting an issue that doesn't exist
    When the client calls DELETE to "/project/Project%20Name/issue/Issue%20Name"
    Then the client should receive a status code of 404