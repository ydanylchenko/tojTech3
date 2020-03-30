Feature:Sign In

  @signIn
  Scenario: Sign in with invalid password
    Given I open start page
    When I set 'standard_user' as username on Sign in page
    And I set 'anotherInvalidPassword' as password on Sign in page
    And I click 'Sign In' button on Sign in page expecting failure
    Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page


Feature: Sign In

  @signIn
  Scenario: Valid user sign in
    Given I open start page

    When I set ' problem_user' as username on Sign in page
    And I set 'problem_user' as password on Sign in page
    And I click 'Sign In' button on Sign in page
    Then I am on Products page

Feature: Sign In

  @signIn
  Scenario: Valid user sign in
    Given I open start page

    When I set '  performance_glitch_user' as username on Sign in page
    And I set 'performance_glitch_user' as password on Sign in page
    And I click 'Sign In' button on Sign in page
    Then I am on Products page