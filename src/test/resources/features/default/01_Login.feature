@regression
Feature: Login Test

  Background: User navigates to Application URL
    Given The Application has been launched

  Scenario Outline: User should not be able to login with invalid credential
    When I enter '<username>' in Username text box
    And I enter '<password>' in Password text box
    And I click on login button
    Then System should display '<errorMsg>' Error Message

    Examples:
      | username        | password  | errorMsg                                                                  |
      |                 |           | Epic sadface: Username is required                                        |
      |                 | test123   | Epic sadface: Username is required                                        |
      | test            |           | Epic sadface: Password is required                                        |
      | test            | test123   | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user | _password | Epic sadface: Sorry, this user has been locked out.                       |

  Scenario Outline: User should be able to login with valid credential
    When I enter '<username>' in Username text box
    And I enter '<password>' in Password text box
    And I click on login button
    Then System should display page header 'PRODUCTS'

    Examples:
      | username                | password  |
      | standard_user           | _password |
      | problem_user            | _password |
      | performance_glitch_user | _password |

  @ignore
  Scenario: I should be able to login as a Standard User
    Then I log in as standard user

  @ignore
  Scenario: I should be able to login as any type of user
    Then I log in as 'problem_user' user