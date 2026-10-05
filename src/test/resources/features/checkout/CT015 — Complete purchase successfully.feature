Feature: checkout

  @CT015
  Scenario: Complete purchase successfully

    Given I added a product to cart
    When I finish the purchase
    Then the success message "Your order has been dispatched, and will arrive just as fast as the pony can get there!" should be displayed