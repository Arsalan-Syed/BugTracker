Feature: user should be able to delete a project

  Scenario: Should delete a project
    Given Project "defaultProject"
      | name         | color   |
      | Project Name | #0000ff |
    And the client calls POST to "/project" with "defaultProject"
    When the client calls DELETE to "/project/Project%20Name"
    Then the client should receive a status code of 200
    And the Project "defaultProject" is no longer in the database