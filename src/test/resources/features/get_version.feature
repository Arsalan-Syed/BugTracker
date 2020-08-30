Feature: The client can fetch the version

  Scenario: Get the version
  When the client calls GET to "/version"
  Then the client receives a version of "1.0"