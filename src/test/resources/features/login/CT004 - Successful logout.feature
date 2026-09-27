Feature: login

  @CT004
  Scenario: Successful logout

    Given I am logged in
    When I log out
    Then I should be logged out successfully