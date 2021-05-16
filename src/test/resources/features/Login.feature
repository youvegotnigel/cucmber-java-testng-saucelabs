Feature: Login Test

  Background: User navigates to Application URL
    Given The Application has been launched

  @regression
  Scenario Outline: User should not be able to login with invalid credential
    When I enter '<username>' Username in text box
    And I enter '<password>' Password in text box
    And I click on login button
    Then System should display '<errorMsg>' Error Message

    Examples:
      | username | password | errorMsg                                                                  |
      |          |          | Epic sadface: Username is required                                        |
      |          | test@123 | Epic sadface: Username is required                                        |
      | test     |          | Epic sadface: Password is required                                        |
      | test     | test@123 | Epic sadface: Username and password do not match any user in this service |

  @regression
  Scenario Outline: User should be able to login with valid credential
    When I enter '<username>' Username in text box
    And I enter '<password>' Password in text box
    And I click on login button
    Then System should display page header 'PRODUCTS'

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |

