Feature: Sign In

  @signIn
  Scenario: Sign in with invalid password
    Given I open start page
    When I set 'problem_user' as username on Sign in page
    And I set 'secret_juice' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page

  @signIn
  Scenario: Sign in with invalid username
    Given I open start page
    When I set 'unusual_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page

  @signIn
  Scenario: Sign in with invalid username and password
    Given I open start page
    When I set 'Iamnot_user' as username on Sign in page
    And I set 'Idonot_care' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page

  @signIn
  Scenario: Sign in with invalid username
    Given I open start page
    When I set '111_222' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page

  @signIn
  Scenario: Sign in with invalid password
    Given I open start page
    When I set 'performance_glitch_user' as username on Sign in page
    And I set '!!@@##$$' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page





