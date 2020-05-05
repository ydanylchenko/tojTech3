Feature: Cart

  Scenario Outline: Verify that user can add item to cart from item page
    Given I open Start page
    When I set 'java selenium' as search criteria on Start page
    And I click on 'search' button on Start page
    And I am on Search results page
    And I click on 'Selenium WebDriver with Java for beginners' course name on Search results page
    Then I am on Course page
    When I click on 'Add to cart' button on Course page
    And I am on Purchase page
    When I click on 'Go to Cart' button on Purchase page
    Then I am on Cart page
    Then Cart contains 'Selenium WebDriver with Java for beginners' course on Cart page
    And Cart subtotal is '$13.99' on cart page

    Scenarios:
    |try|
    |1|
    |1|
    |1|
    |1|
    |1|