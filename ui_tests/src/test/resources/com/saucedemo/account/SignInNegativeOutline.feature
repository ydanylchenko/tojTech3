Feature: Sign In

  Background:
    Given I open start page

  @signIn
  Scenario Outline: Sign in with invalid password
    When I set '<Username>' as username on Sign in page
    And I set '<Password>' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page

    Examples:
      | Username        | Password        |
      | Problem_user    | secret_juice    |
      | unusual_user    | secret_juice    |
      | Iamnot_user     | secret_juice    |
      | 111_222         | Idonot_care     |
      | '         '     | '         '     |
      | locked_out_user | victoria_secret |
