@regression
Feature: Products Functionality

  Background: User navigates to Application URL
    Given I log in as standard user

  Scenario: Verify shopping cart badge is displayed
    Then  Shopping cart badge should be displayed

  Scenario: Verify all inventory item descriptions are displayed
    Then  I should see 6 inventory descriptions

  Scenario: Verify all inventory item images are displayed
    Then  I should see 6 inventory item images

  Scenario: Verify inventory item names are in ascending order
    When I click on filter icon
    And Select 'Name (A to Z)' from the dropdown
    Then Item 'names' should be in ascending order

  Scenario: Verify inventory item names are in descending order
    When I click on filter icon
    And Select 'Name (Z to A)' from the dropdown
    Then Item 'names' should be in descending order

  Scenario: Verify inventory item prices are in ascending order
    When I click on filter icon
    And Select 'Price (low to high)' from the dropdown
    Then Item 'prices' should be in ascending order

  Scenario: Verify inventory item prices are in descending order
    When I click on filter icon
    And Select 'Price (high to low)' from the dropdown
    Then Item 'prices' should be in descending order

  Scenario: Verify Add/Remove items from cart
    When I add 'Sauce Labs Fleece Jacket' to my cart
    And I add "Sauce Labs Backpack" to my cart
    And I add "Sauce Labs Bolt T-Shirt" to my cart
    And I add "Test.allTheThings() T-Shirt (Red)" to my cart
    And I add "Sauce Labs Bike Light" to my cart
    And I remove "Test.allTheThings() T-Shirt (Red)" from my cart
    And I remove "Sauce Labs Fleece Jacket" from my cart
    Then There should be "3" items in the cart

