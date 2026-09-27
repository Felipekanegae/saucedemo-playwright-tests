Feature: login

  @CT003
  Scenario: Login without credentials

    Given I am on the login page
    When I try to login without credentials
    Then the message "Epic sadface: Username is required" should be displayed