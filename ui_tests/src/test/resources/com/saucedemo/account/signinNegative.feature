Feature: Sign In


  @signIn
  Scenario: Sign in with invalid password
    Given I open start page
    When I set 'standard_user' as username on Sign in page
    And I set 'thisIsSomeInvalidPassword' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page


  @signIn
  Scenario: Sign in with invalid user name
    Given I open start page
    When I set 'thisIsSomeInvalidUser' as username on Sign in page
    And I set 'standard_password' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page

  @signIn
  Scenario: Sign in with empty user and password
    Given I open start page
    When I set ' ' as username on Sign in page
    And I set ' ' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page

  Scenario: Sign in with valid user name and empty password
    Given I open start page
    When I set 'standard_user' as username on Sign in page
    And I set ' ' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page

  Scenario: Sign in with empty user name and valid password
    Given I open start page
    When I set ' ' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page

  Scenario Outline: Sign in
    Given I open start page
    When I set '<username>' as username on Sign in page
    And I set '<password>' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message '<Error message>' is available on Sign in page
    Examples:
      | username                | password     | Error message                                            |
      | standard_user           |              | Epic sadface: Password is required                 |
      |                         | secret_sauce | Epic sadface: Username is required                 |
      | locked_out_user         | secret_sauce | Epic sadface: Sorry, this user has been locked out.|