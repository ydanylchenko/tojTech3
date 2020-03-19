Feature: Sign In
@signIn
Scenario: Sign in with empty username and password
Given I open start page
When I set ' ' as username on Sign in page
And I set ' ' as password on Sign in page
And I click 'Sign In' button on Sign in page expecting failure
Then Error message 'Epic sadface: Username is required' is available on Sign in page


@signIn
Scenario: Valid user sign in
Given I open start page
When I set 'standard_user' as username on Sign in page
And I set '' as password on Sign in page
And I click 'Sign In' button on Sign in page expecting failure
Then Error message 'Epic saeeeeeeedface: Password is required' is available on Sign in page

Feature: Sign In
@signIn
Scenario: Sign in with invalid password
Given I open start page
When I set 'locked_out_user' as username on Sign in page
And I set 'secret_sauce' as password on Sign in page
And I click 'Sign In' button on Sign in page expecting failure
Then Error message 'Epic sadface: Sorry, this user has been locked out.' is available on Sign in page