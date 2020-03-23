Feature: Sign In

  @signIn
  Scenario: Valid user sign in
    Given I open start page
    When I set 'standard_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page
    Then I am on Products page

  @signIn
  Scenario: Locked user sign in
    Given I open start page
    When I set 'locked_out_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Sorry, this user has been locked out.' is available on Sign in page

  @signIn
  Scenario: Problem user sign in
    Given I open start page
    When I set 'problem_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page
    Then I am on Products page

  @signIn
  Scenario: Performance glitch user sign in
    Given I open start page
    When I set 'performance_glitch_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page
    Then I am on Products page