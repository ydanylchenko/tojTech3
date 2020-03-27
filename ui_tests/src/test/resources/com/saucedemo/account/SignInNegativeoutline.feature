Feature: Sign In

  Background:
    And I open start page

  @signIn
  Scenario Outline: Verify <username> empty user name sign in with empty <password> password
    When  I set '<username>' as username on Sign in page
    And I set '<password>' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Examples:
      | username        | password     |
      |                 |              |
      | qwerty          | secret_sauce |
      | locked_out_user | secret_sauce |

  Scenario Outline: Verify <Error message> Epic sadface: is available on Sign in page

    Examples:
    |
    |
    |
    |