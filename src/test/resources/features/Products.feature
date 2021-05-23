Feature: Products Functionality

  Background: User navigates to Application URL
    Given I log in as standard user

  @regression
  Scenario: Verify shopping cart badge is displayed
    Then  Shopping cart badge should be displayed

  @regression
  Scenario: Verify inventory item names are in ascending order
    When I click on filter icon
    And Select 'Name (A to Z)' from the dropdown
    Then Item 'names' should be in ascending order

  @regression
  Scenario: Verify inventory item names are in descending order
    When I click on filter icon
    And Select 'Name (Z to A)' from the dropdown
    Then Item 'names' should be in descending order

  @regression
  Scenario: Verify inventory item prices are in ascending order
    When I click on filter icon
    And Select 'Price (low to high)' from the dropdown
    Then Item 'prices' should be in ascending order

  @regression
  Scenario: Verify inventory item prices are in descending order
    When I click on filter icon
    And Select 'Price (high to low)' from the dropdown
    Then Item 'prices' should be in descending order

