Feature: The client can get all issues

  Scenario: Should be able to get all issues for a given project
    Given Project "defaultProject"
      | name         | color   |
      | ProjectName  | #0000ff |
    And Issue "issue1"
      | name         |
      | Issue Name 1 |
    And Issue "issue2"
      | name         |
      | Issue Name 2 |
    And the client calls POST to "/project" with "defaultProject"
    And the client calls POST to "/project/ProjectName/issue" with "issue1"
    And the client calls POST to "/project/ProjectName/issue" with "issue2"

    When the client calls GET to "/project/ProjectName/issues"
    Then the client should receive a status code of 200
    And the response contains issues
      | issue1 |
      | issue2 |


  Scenario: Should return an empty collection if no issues exist for a project
    Given Project "defaultProject"
      | name         | color   |
      | ProjectName  | #0000ff |
    And the client calls POST to "/project" with "defaultProject"

    When the client calls GET to "/project/ProjectName/issues"
    Then the client should receive a status code of 200
    And the response contains no issues