Feature: Log Out

  @LogOut
  Scenario: Log out with valid username and password
    Given I open start page
    Then I set 'standard_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page
    And I am on Products page
    And I click on menu icon on Header
    And I click 'Logout' link on Menu
    Then I am on Sign in page


  @LogOut
  Scenario: Log out with valid username and password
    Given I open start page
    Then I set 'performance_glitch_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page
    And I am on Products page
    And I click on menu icon on Header
    And I click 'Logout' link on Menu
    Then I am on Sign in page