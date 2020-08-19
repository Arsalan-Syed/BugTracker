Feature: The client can create a project

  Scenario: Create a project
    Given Project "defaultProject"
    | name         | color   |
    | Project Name | #0000ff |
    When the client calls POST to "/project" with "defaultProject"
    Then the client should receive a status code of 200
    And I can retrieve Project "defaultProject" from the database
    And the Project "defaultProject" matches exactly


  Scenario: Reject creating a project if the name is empty
    Given Project "defaultProject"
      | name | color   |
      |      | #00000f |
    When the client calls POST to "/project" with "defaultProject"
    Then the client should receive a status code of 400


  Scenario: Should not create a project if a project with same name already exists
    Given Project "defaultProject"
      | name         | color   |
      | Project Name | #0000ff |
    And Project "defaultProjectWithDuplicateName"
      | name         | color   |
      | Project Name | #0000ff |
    And the client calls POST to "/project" with "defaultProject"

    When the client calls POST to "/project" with "defaultProject"
    Then the client should receive a status code of 400