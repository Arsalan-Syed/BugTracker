Feature: Should be able to assign a user with a developer role to an issue

  Scenario: Assign developer to issue
    Given CreateUserRequest "userRequest"
      | username | password | matchPassword | email             | name         |
      | jake_12  | Ppdj1#34 | Ppdj1#34      | jake_12@gmail.com | Jake J Smith |
    And the client calls POST to "/register" with "userRequest"

    And Project "defaultProject"
      | name         | color   |
      | Project Name | #0000ff |
    And the client calls POST to "/project" with "defaultProject"

    And Issue "defaultIssue"
      | name        | priority |
      | Issue Name  | LOW      |
    And the client calls POST to "/project/Project%20Name/issue" with "defaultIssue"

    When the client assigns an issue to developer with username "jake_12"
    Then the client should receive a status code of 200