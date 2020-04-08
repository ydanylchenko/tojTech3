Feature: Sign In
Background:
  Given I open start page
  @signIn

  Scenario: Sign in with empty username and password
    When  I set '' as username on Sign in page
    And   I set '' as password on Sign in page
    And   I click 'Sign In' button on Sign in page expecting failure
    Then  Error message 'Epic sadface: Username is required' is available on Sign in page

  Scenario: Sign in with empty password
    When  I set 'standard_user' as username on Sign in page
    And   I set '' as password on Sign in page
    And   I click 'Sign In' button on Sign in page expecting failure
    Then  Error message 'Epic sadface: Password is required' is available on Sign in page

  Scenario: Sign in as locked_out_user
    When  I set 'locked_out_user' as username on Sign in page
    And   I set 'secret_sauce' as password on Sign in page
    And   I click 'Sign In' button on Sign in page expecting failure
    Then  Error message 'Epic sadface: Sorry, this user has been locked out.' is available on Sign in page

  Scenario: Sign in with invalid password as changing lower case with upper case
    When  I set 'standard_user' as username on Sign in page
    And   I set 'Secret_Sauce' as password on Sign in page
    And   I click 'Sign In' button on Sign in page expecting failure
    Then  Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page

  Scenario: Sign in as standard_user but writing password without underscore
    When  I set 'standard_user' as username on Sign in page
    And   I set 'secretsauce' as password on Sign in page
    And   I click 'Sign In' button on Sign in page expecting failure
    Then  Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page

  Scenario: Sign in with Invalid Password
    When  I set 'problem_user ' as username on Sign in page
    And   I set 'problem_user' as password on Sign in page
    And   I click 'Sign In' button on Sign in page expecting failure
    Then  Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page

  Scenario Outline: Sign in
    When  I set '<username>' as username on Sign in page
    And   I set '<password>' as password on Sign in page
    And   I click 'Sign In' button on Sign in page expecting failure
    Then  Error message '<Error message>' is available on Sign in page
    Examples:
    | username        | password     | Error message |
    | standart_user   |              | Epic sadface: Password is required |
    |                 | secret_sauce | Epic sadface: Username is required |
    | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.|
    | standartuser    |              | Epic sadface: Password is required |
    | standartuser    | secret_sauce | Epic sadface: Epic sadface: Username and password do not match any user in this service |
    | locked_out_user |              | Epic sadface: Password is required.|
