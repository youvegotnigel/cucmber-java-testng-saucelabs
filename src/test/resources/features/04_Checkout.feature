@regression
Feature: Checkout Functionality

  Background: User navigates to Application URL
    Given I log in as standard user
    And I add all items to cart
    And I click on Shopping cart badge
    And I click on checkout button

  Scenario: Verify page navigation to Checkout
    Then  System should display page header 'CHECKOUT: YOUR INFORMATION'

  Scenario: Verify error validation messages
    When  I click on continue button
    Then Error message Error: First Name is required should be displayed
    When I input Ben for First Name
    And I click on continue button
    Then Error message Error: Last Name is required should be displayed
    When I input Dover for Last Name
    And I click on continue button
    Then Error message Error: Postal Code is required should be displayed

  Scenario: Verify successful purchase
    Given  I click on continue button
    And I input Ben for First Name
    And I input Dover for Last Name
    And I input 99999 for Zip Code
    When I click on continue button
    Then System should display page header 'CHECKOUT: OVERVIEW'
    And Should display shipping information FREE PONY EXPRESS DELIVERY!
    And Should display label Item total: $129.94
    And Should display label Tax: $10.40
    And Should display label Total: $140.34
    When Click on finish button
    Then  System should display page header 'CHECKOUT: COMPLETE!'
    And Should display label THANK YOU FOR YOUR ORDER
    And I wait for "2" seconds
    And Pony Express delivery logo should be displayed
