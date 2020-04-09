@signIn
Scenario: Sign in with ddaa username and password
Given I open start page
When I set 'ddaa' as username on Sign in page
And I set 'ddaa' as password on Sign in page
And I click 'Sign In' button on Sign in page expecting failure
Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page



@signIn
Scenario: Sign in with dddd password
Given I open start page
When I set 'standard_user' as username on Sign in page
And I set 'dddd' as password on Sign in page
And I click 'Sign In' button on Sign in page expecting failure
Then Error message 'Epic sadface: Username and password do not match any user in this service' is available on Sign in page




