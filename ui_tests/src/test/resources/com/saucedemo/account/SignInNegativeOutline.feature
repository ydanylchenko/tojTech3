Feature: Sign In

  Background:
    Given I open start page

  @signIn
  Scenario Outline: Sign in with invalid password
    When I set '<Username>' as username on Sign in page
    And I set '<Password>' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message '<Message>' is available on Sign in page

    Examples:
      | Username        | Password        | Message                                                                   |
      | Problem_user    | secret_juice    | Epic sadface: Username and password do not match any user in this service |
      | unusual_user    | secret_juice    | Epic sadface: Username and password do not match any user in this service |
      | Iamnot_user     | secret_juice    | Epic sadface: Username and password do not match any user in this service |
      | 111_222         | Idonot_care     | Epic sadface: Username and password do not match any user in this service |
      |                 |                 | Epic sadface: Username is required                                        |
      | locked_out_user | victoria_secret | Epic sadface: Username and password do not match any user in this service |
