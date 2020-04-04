
@signIn
Scenario: Valid user sign in
Given I open start page
When I set 'standard_user' as username on Sign in page
And I set 'secret_sauce' as password on Sign in page
And I click 'Sign In' button on Sign in page
Then I am on Products page

@signIn
Scenario: Sign in with valid password
Given I open start page
When I set 'epic_user' as username on Sign in page
And I set 'secret_sauce' as password on Sign in page
And I click 'Sign In' button on Sign in page
Then I am on Products page
