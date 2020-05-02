Feature: Sign In

  Scenario: Sign in as jenkinsUser
    When I open Dashboard page
    And I click log in link on Dashboard page
    And I set jenkinsUser as username on Login page
    And I set 123123 as password on Login page
    And I click Sign in button on Login page
    Then I am signed in as jenkinsUser user on Dashboard page


# DONE  1. Returns from step definitions
# DONE  2. Synchronization between page objects (constructors)
#  3. Polish everything
# DONE 4. Step definitions (and steps) are not dynamic - -we cannot pass values to the step definition from feature file
