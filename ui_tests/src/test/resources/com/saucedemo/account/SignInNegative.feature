Feature: Sign In with Invalid data

  @signIn
  Scenario: When the fields is blank and Submit button is clicked.
    Given I open start page
    When Username field is blank on Sign in page
    And Password field is blank on Sign in page
    And I click "LOGIN" button on Sign in page, expected result: "Username and Password are required"
    Then Error message 'Epic sadface: Username is required'


  @signIn
  Scenario: User can't log in with unregistered login
    Given I open start page
    When I put random username
    And I put random password
    And I click "LOGIN" button on Sign in page, expected result: "Sign up for an account"
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page


  @signIn
  Scenario: User Should not log in with the same Username and Password
    Given I open start page
    When I put username
    And I put the same password as username
    And I click "LOGIN" button on Sign in page, expected result: "The password you’ve entered is incorrect".
    Then Error message "Epic sadface: Username and password do not match any user in this service"


  @signIn
  Scenario: User should not log in with Username 'locked_out_user' and Password 'secret_sauce'
    Given I open start page
    When I put Username 'locked_out_user'
    And I put Password 'secret_sauce'
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Sorry, this user has been locked out' is available on Sign in page


   @signIn
   Scenario: Sign In with blank password
     Given I open start page
     When I set 'problem_user' as username on Sign in page
     And I set '' as password on Sign in page
     And I click 'Sign In' button on Sign in page expecting failure
     Then Error message 'Epic sadface: Password is required' is available on Sign in page



















