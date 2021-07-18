Feature: Template

    ## Login
  Scenario: Template for Login Scenarios
    Then I log in as standard user
    Then I log in as 'problem_user' user

    ##Waits
  Scenario: Template for Waits Scenarios
    And I wait for "2" seconds

    ## Set text
  Scenario: Template for Set Text Scenarios
    And I input Ben for First Name
    And I input Dover for Last Name
    And I input 99999 for Zip Code
    And I set value "Ben" for "First Name"
    And I set value "24" for "Age[2]"

    ## Click link/button
  Scenario: Template for Click link/button Scenarios
    And I click on login button
    And I click on '<button_name>' button
    And I click on '<link_name>' link
    And I click on the '<normalize_name>' button
    And I click on the '<normalize_name>' link
    And I click on the '<normalize_name>' text

    ## Is displayed text
  Scenario: Template for is text displayed Scenarios
    And I should see the text '<any_text>' displayed
    And I should not see the text '<any_text>' displayed

    ## Select from dropdown
  Scenario: Template for Select from dropdown Scenarios

    ## Radio button
  Scenario: Template for Radio button Scenarios

    ## Check box
  Scenario: Template for Check box Scenarios

    ## Tables
  Scenario: Template for Tables Scenarios
    Then I should see the items in my cart as below:
      | item_name                         | item_price |
      | Sauce Labs Backpack               | $29.99     |
      | Sauce Labs Bike Light             | $9.99      |
      | Sauce Labs Bolt T-Shirt           | $15.99     |
      | Sauce Labs Fleece Jacket          | $49.99     |
      | Sauce Labs Onesie                 | $7.99      |
      | Test.allTheThings() T-Shirt (Red) | $15.99     |

    ## Ascending/Descending Order
  Scenario: Template for Ascending/Descending Order Scenarios
    Then Item 'names' should be in descending order
    Then Item 'names' should be in ascending order
    Then Item 'prices' should be in descending order
    Then Item 'prices' should be in ascending order

  Scenario: ## Table Ascending/Descending
    Then Get values for "Name" column in table and verify strings in descending order
    Then Get values for "ID" column in table and verify numbers in descending order
    Then Get values for "Name" column in table and verify strings in ascending order
    Then Get values for "ID" column in table and verify numbers in ascending order
