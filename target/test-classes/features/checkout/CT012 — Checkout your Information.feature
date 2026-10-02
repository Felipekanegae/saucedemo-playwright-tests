Feature: checkout

  @CT012
  Scenario: Fill checkout information successfully

    Given I added a product to cart
    And I am on the checkout information page
    When I fill in the required checkout information
    Then I should be on the checkout overview page