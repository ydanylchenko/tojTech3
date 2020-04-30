Feature: Sign In

  Scenario: Sign in as jenkinsUser
    When I open dashboard page
    And sleep
    And I click log in link on Dashboard page
    And sleep
    And I set THIS as username on Login page
    And sleep
    And I set THIS as password on Login page
    And sleep
    And I click Sign in button on Login page
    And sleep
    Then I am signed in as THIS user on Dashboard page
