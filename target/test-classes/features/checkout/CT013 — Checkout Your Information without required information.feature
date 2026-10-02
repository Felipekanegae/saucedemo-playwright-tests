Feature: checkout

  @CT013
  Scenario: Checkout without required information

    Given I added a product to cart
    And I am on the checkout information page
    When I try to continue without filling in the required information
    Then The message "Error: First Name is required" should be displayed