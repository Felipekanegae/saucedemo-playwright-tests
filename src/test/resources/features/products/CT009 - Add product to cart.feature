Feature: products

  @CT009
  Scenario: Add product to cart

    Given I am on the products page
    When I add a product to cart
    Then the product should be in the cart
