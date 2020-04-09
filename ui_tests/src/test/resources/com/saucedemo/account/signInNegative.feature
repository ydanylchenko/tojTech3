Feature: Sign In

  @signIn
  Scenario: Sign in with empty username and password
    Given I open start page
    When I set '' as username on Sign in page
    And I set '' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username is required' is available on Sign in page

  @signIn
  Scenario: Sign in with empty password
    Given I open start page
    When I set 'standard_user' as username on Sign in page
    And I set '' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Password is required' is available on Sign in page

  @signIn
  Scenario: Sign in as locked out user
    Given I open start page
    When I set 'locked_out_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Sorry, this user has been locked out.' is available on Sign in page

  @signIn
  Scenario Outline: Verify <username> user cannot sign in with <password> password
    Given I open start page
    When I set '<username>' as username on Sign in page
    And I set '<password>' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message '<errorMessage>' is available on Sign in page

    Examples:
      | username        | password        | errorMessage                                                              |
      | standard_user   |                 | Epic sadface: Password is required                                        |
      |                 | secret_sauce    | Epic sadface: Username is required                                        |
      | locked_out_user | secret_sauce    | Epic sadface: Sorry, this user has been locked out.                       |
      | sdsdfsdffv      | sdfgsdfgdfs     | Epic sadface: Username and password do not match any user in this service |
      | standard_user   | invalidPassword | Epic sadface: Username and password do not match any user in this service |

