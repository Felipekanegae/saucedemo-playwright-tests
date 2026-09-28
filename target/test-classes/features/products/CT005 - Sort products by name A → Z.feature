Feature: products

  @CT005
  Scenario: Sort products by name A to Z

    Given I am on the products page
    When I sort the products by name in ascending order
    Then the name ascending sort option should be selected