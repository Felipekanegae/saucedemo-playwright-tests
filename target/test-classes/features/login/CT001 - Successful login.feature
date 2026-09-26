Feature: login

  @CT001
  Scenario: Login with valid credentials

    Given I am on the login page
    When I enter a valid user name and password
    Then the user should be logged in successfully