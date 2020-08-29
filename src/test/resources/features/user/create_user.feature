Feature: The client can create an account

  Scenario: Should successfully create a new account
    Given CreateUserRequest "userRequest"
    | username | password | matchPassword | email             | name       |
    | jake_12  | Ppdj1#34 | Ppdj1#34      | jake_12@gmail.com | Jake J Smith |
    When the client calls POST to "/register" with "userRequest"
    Then the client should receive a status code of 200
    And I can retrieve the user "jake_12" from the database