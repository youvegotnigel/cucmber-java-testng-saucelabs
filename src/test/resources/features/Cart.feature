@regression
Feature: Add to Cart Functionality

  Background: User navigates to Application URL
    Given I log in as standard user
    And I add all items to cart
    And I click on Shopping cart badge

  Scenario: Verify page navigation to Cart
    Then  System should display page header 'YOUR CART'

  Scenario: Verify all items are displayed
    Then I should see the items in my cart as below:
      | item_name                         | item_price |
      | Sauce Labs Backpack               | $29.99     |
      | Sauce Labs Bike Light             | $9.99      |
      | Sauce Labs Bolt T-Shirt           | $15.99     |
      | Sauce Labs Fleece Jacket          | $49.99     |
      | Sauce Labs Onesie                 | $7.99      |
      | Test.allTheThings() T-Shirt (Red) | $15.99     |



