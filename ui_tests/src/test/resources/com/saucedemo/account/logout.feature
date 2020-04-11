Feature: Logout

  @signIn
  Scenario: Valid user sign in
    Given I open start page
    When I set 'standard_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page
    Then I am on Products page
    And I click on menu icon on Header
    When I click 'Logout' link on Menu
    Then I am on Sign in page

