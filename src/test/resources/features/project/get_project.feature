Feature: The client can retrieve projects from the database

  Scenario: client creates a project and then retrieves it
    Given Project "project1"
      | name          | color   |
      | Project Name1 | #0000ff |
    And Project "project2"
      | name          | color   |
      | Project Name2 | #0000ff |
    And the client calls POST to "/project" with "project1"
    And the client calls POST to "/project" with "project2"
    When the client calls GET to "/projects"
    Then the client should receive a status code of 200
    And the response contains projects
      | project1 |
      | project2 |