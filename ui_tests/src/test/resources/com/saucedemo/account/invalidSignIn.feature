Feature: InvalidSignIn

  @InvalidSignIn
  Scenario Outline: Sign-in with valid and invalid input
    Given I open start page
    When I set '<username>' as username on Sign in page
    And I set '<password>' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message '<ERROR messages>' is available on Sign in page
    Examples:
      | username                | password     | ERROR messages                                                            |
      | standard_user           | 123465789    | Epic sadface: Username and password do not match any user in this service |
      | standard_user           | !@#$%^&*()   | Epic sadface: Username and password do not match any user in this service |
      | standard_user           |              | Epic sadface: Password is required                                        |
      |                         | secret_sauce | Epic sadface: Username is required                                        |
      | standard_user           | secret-sauce | Epic sadface: Username and password do not match any user in this service |
      | Standard_User           | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user         | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
      | locked_out_user         | 123465789    | Epic sadface: Username and password do not match any user in this service |
      | problem_user            | 1@3$5^7*9)   | Epic sadface: Username and password do not match any user in this service |
      | performance_glitch_user | 1a2b3c       | Epic sadface: Username and password do not match any user in this service |
