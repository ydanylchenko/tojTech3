Feature: Sign In
  @signIn
  Scenario: Sign In with valid username and password
    Given I open start page
    When I set 'standard_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page
    Then I am on Products page
  @signIn
  Scenario: Sign In with valid username and password
    Given I open start page
    When I set 'problem_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page
    Then I am on Products page
  @signIn
  Scenario: Sign In with valid username and password
    Given I open start page
    When I set 'performance_glitch_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page
    Then I am on Products page
  @signIn
  Scenario: Sign In with valid username and password
    Given I open start page
    When I set 'locked_out_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page
    Then Error message 'Epic sadface: this user name has been locked out ' is available on Sign in page





