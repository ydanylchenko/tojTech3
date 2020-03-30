Feature: Sign In

  Background:
    And I open start page

  @signIn
  Scenario: Invalid user sign in
    When I set 'standard' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page

  @signIn
  Scenario: Invalid password sign in
    When I set 'standard_user' as username on Sign in page
    And I set 'sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page

  @signIn
  Scenario: Invalid user sign in
    When I set '' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username is required' is available on Sign in page

  @signIn
  Scenario: Invalid password sign in
    When I set 'standard_user' as username on Sign in page
    And I set '' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Password is required' is available on Sign in page

  @signIn
  Scenario Outline: Invalid password sign in
    When I set '<username>' as username on Sign in page
    And I set '<password>' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message '<error message>' is available on Sign in page

    Examples:
      | username        | password     | error message                                       |
      | standart_user   |              | Epic sadface: Password is required                  |
      |                 | secret_sauce | Epic sadface: Username is required                  |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out. |
    