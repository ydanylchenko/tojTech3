Feature: Sign In

  @signIn
  Scenario: sign in with empty user name and password
    Given  I open start page
    When  I set '' as username on Sign in page
    And I set '' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username is required' is available on Sign in page


  @signIn
  Scenario: sign in with invalid username
    Given  I open start page
    When  I set 'qwerty' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page


  @signIn
  Scenario: sign in with locked username and password
    Given  I open start page
    When  I set 'locked_out_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Sorry, this user has been locked out.' is available on Sign in page
