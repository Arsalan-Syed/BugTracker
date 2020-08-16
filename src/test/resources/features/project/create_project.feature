Feature: The client can create a project
  Scenario: Create a project
    When the client calls POST to "/project" with body
    """
    {
      "name": "Project Name"
      "color": "#00000f
    }
    """
    Then the client should receive a status code of 200