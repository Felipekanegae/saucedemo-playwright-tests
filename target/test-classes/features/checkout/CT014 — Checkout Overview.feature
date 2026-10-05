Feature: checkout

  @CT014
  Scenario: Checkout overview

    Given I added a product to cart
    When I go to the checkout overview page
    Then the added product should be displayed on the overview page