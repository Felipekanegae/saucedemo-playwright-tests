Feature: login

  @CT002
  Scenario: Login with locked out user

    Given I am on the login page
    When I enter a locked out user
    Then the message "Epic sadface: Sorry, this user has been locked out." should be displayed