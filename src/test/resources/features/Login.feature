Feature: Login Test

  Scenario Outline: User should not be able to login with invalid credential
    Given The Application has been launched
    And I enter '<username>' Username in text box
    And I enter '<password>' Password in text box
    And I click on login button
    Then System should display '<errorMsg>' Error Message

    Examples:
      | username | password | errorMsg                                                                  |
      #|          |          | Epic sadface: Username is required                                        |
      #|          | test@123 | Epic sadface: Username is required                                        |
      #| test     |          | Epic sadface: Password is required                                        |
      | test     | test@123 | Epic sadface: Username and password do not match any user in this service |

