Feature: Sign In


  @signIn

  Scenario: Sign in with empty username and password
    Given  I open start page
    When  I set '' as username on Sign in page
    And   I set ' ' as password on Sign in page
    And   I click 'Sign In' button on Sign in page expecting failure
    Then  Error message 'Epic sadface: Username is required' is available on Sign in page




  Scenario: Sign in with empty password
    Given I open start page
    When  I set 'standard_user' as username on Sign in page
    And   I set '' as password on Sign in page
    And   I click 'Sign In' button on Sign in page expecting failure
    Then  Error message 'Epic sadface: Password is required' is available on Sign in page



  Scenario: Sign in as locked_out_user
    Given I open start page
    When  I set 'locked_out_user' as username on Sign in page
    And   I set 'secret_sauce' as password on Sign in page
    And   I click 'Sign In' button on Sign in page expecting failure
    Then  Error message 'Epic sadface: Sorry, this user has been locked out.' is available on Sign in page



  Scenario: Sign in with invalid password as changing lower case with upper case
    Given I open start page
    When  I set 'standard_user' as username on Sign in page
    And   I set 'secret_Sauce' as password on Sign in page
    And   I click 'Sign In' button on Sign in page expecting failure
    Then  Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page

  Scenario: Sign in as standard_user but writing password without underscore between ( secretsauce )
    Given I open start page
    When  I set 'standard_User' as username on Sign in page
    And   I set 'secretsauce' as password on Sign in page
    And   I click 'Sign In' button on Sign in page expecting failure
    Then  Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page



  Scenario: Sign in with Invalid Password
    Given I open start page
    When  I set 'problem_user ' as username on Sign in page
    And   I set 'problem_user' as password on Sign in page
    And   I click 'Sign In' button on Sign in page expecting failure
    Then  Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page











