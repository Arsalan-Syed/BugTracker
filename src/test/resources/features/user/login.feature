Feature: The client can login with their account

  Scenario: Should receive a JWT token when logged in successfully
    Given CreateUserRequest "userRequest"
    | username | password | matchPassword | email             | name         |
    | jake_12  | Ppdj1#34 | Ppdj1#34      | jake_12@gmail.com | Jake J Smith |
    And the client calls POST to "/register" with "userRequest"
    When the client calls POST to "/login" with body
    """
        {
        "username":"jake_12",
        "password":"Ppdj1#34"
        }
    """
    Then the client should receive a status code of 200
    And the client should receive a JWT token


  Scenario: Should reject login if password is incorrect
    Given CreateUserRequest "userRequest"
      | username | password | matchPassword | email             | name         |
      | jake_12  | Ppdj1#34 | Ppdj1#34      | jake_12@gmail.com | Jake J Smith |
    And the client calls POST to "/register" with "userRequest"
    When the client calls POST to "/login" with body
    """
        {
        "username":"jake_12",
        "password":"WrongPassword"
        }
    """
    Then the client should receive a status code of 401