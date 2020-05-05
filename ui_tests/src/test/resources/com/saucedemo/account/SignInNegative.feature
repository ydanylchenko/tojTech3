Feature: Sign In with Invalid data

  Background:
    And I open start page

  @signIn
  Scenario Outline: Invalid user sign in
    When I set '<username>' as username on Sign in page
    And I set '<password>' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message '<Error message>' is available on Sign in page


    Examples:
      | username        | password     | Error message                                                             |
      | standard_user   |              | Epic sadface: Password is required                                        |
      |                 | secret_sauce | Epic sadface: Username is required                                        |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
      | jdhghjghgjdffh  | ghsdgfhsghfg | Epic sadface: Username and password do not match any user in this service |