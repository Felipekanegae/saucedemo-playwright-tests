Feature: products

  @CT007
  Scenario: Sort products by price low to high

    Given I am on the products page
    When I sort the products by price in ascending order
    Then the price ascending sort option should be selected

