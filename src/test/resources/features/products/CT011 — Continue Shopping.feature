Feature: products

  @CT011
  Scenario: Continue shopping

    Given I added a product to cart
    When I am on the cart page
    And I click on continue shopping
    Then I should be on the products page
