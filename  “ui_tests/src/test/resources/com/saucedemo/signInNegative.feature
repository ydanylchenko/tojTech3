Feature: Sign In


  @signIn

  Scenario: Sign in with empty username and  password

    Given I open start page

    When I set '  ' as username on Sign in page

    And I set '   ' as password on Sign in page

    And I click ' Sign in ' button on Sign in page expecting failure

    Then Error message  'Epic sadface: Username is required '


  Scenario: Sign in with invalid password

    Given  I open start page

    When I set 'standard_user' as username on Sign in page

    And I set '     ' as password on Sign in page

    And I click 'Sign in' button on Sign in page expecting failure

    Then  Error message : Epic sadface: Password is required

  Scenario : Sign in with invalid credentials for password

    Given I open start page

    When I set 'standard_user' as username on Sign in page

    And I set ' numbers 1 and 2 and 3  instead of letters ' on Sign in page

    And I click 'Sign in' button on Sign in page expecting failure

    Then  Error message :'Epic sadface: Sorry this user been locked out '




