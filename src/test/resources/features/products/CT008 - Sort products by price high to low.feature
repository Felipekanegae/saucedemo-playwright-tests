Feature: products

  @CT008
  Scenario: Sort products by price high to low

    Given I am on the products page
    When I sort the products by price in descending order
    Then the price descending sort option should be selected

