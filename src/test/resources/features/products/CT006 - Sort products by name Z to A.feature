Feature: products

  @CT006
  Scenario: Sort products by name Z to A

    Given I am on the products page
    When I sort the products by name in descending order
    Then the name descending sort option should be selected