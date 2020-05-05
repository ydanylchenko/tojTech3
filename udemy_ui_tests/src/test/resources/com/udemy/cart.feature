Feature: Cart

  Scenario Outline: Verify that user can add item to cart from item page searched by <searchKeyword>
    Given I open Start page
    When I set '<searchKeyword>' as search criteria on Start page
    And I click on 'search' button on Start page
    And I am on Search results page
    And I click on '<courseName>' course name on Search results page
    Then I am on Course page
    When I click on 'Add to cart' button on Course page
    And I am on Purchase page
    When I click on 'Go to Cart' button on Purchase page
    Then I am on Cart page
    Then Cart contains '<courseName>' course on Cart page
    And Cart subtotal is '<price>' on cart page

    Scenarios:
      | searchKeyword | courseName                                 | price  |
      | java selenium | Selenium WebDriver with Java for beginners | $13.99 |
      | python        | Learn Python Programming Masterclass       | $15.99 |
