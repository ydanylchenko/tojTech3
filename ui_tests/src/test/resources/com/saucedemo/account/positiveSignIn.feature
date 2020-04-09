Feature: Positive Sign-in

  @PositiveSignIn
  Scenario Outline: Sign-in with valid input
    Given I open start page
    When I set '<username>' as username on Sign in page
    And I set '<password>' as password on Sign in page
    And I click 'Sign In' button on Sign in page
    Then I am on Products page
    And I click on menu icon on Header
    And I click 'Logout' link on Menu
    And I am on Sign in page
    Examples:
      | username                | password     | ERROR messages |
      | standard_user           | secret_sauce |                |
      | problem_user            | secret_sauce |                |
      | performance_glitch_user | secret_sauce |                |

  @PositiveSignIn
  Scenario Outline: Sign-in with valid input for locked user
    Given I open start page
    When I set '<username>' as username on Sign in page
    And I set '<password>' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message '<ERROR messages>' is available on Sign in page
    Examples:
      | username        | password     | ERROR messages                                      |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out. |