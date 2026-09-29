Feature: products

  @CT010
  Scenario: Remove product from cart

    Given I added a product to cart
    When I am on the cart page
    And I remove the product from cart
    Then the product should be removed
