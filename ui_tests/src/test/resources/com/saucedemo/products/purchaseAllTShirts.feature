Feature: Purchase

  Background:
    Given I open start page
    And I set 'standard_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page

  @purchase
  Scenario: Purchase all the T-shirts
    When I click 'ADD TO CART' button on ' Labs Bolt T-Shirt' product on Products page
    Then Cart contains '1' items on Header
    When I click 'ADD TO CART' button on 'Test.allTheThings() T-Shirt (Red)' product on Products page
    Then Cart contains '2' items on Header
    When I click on cart icon on Header
    And The following products are available on Cart page:
      | quantity | product                           | description                                                                                                                                               | price |
      | 1        | Sauce Labs Bolt T-Shirt           | Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.           | 15.99 |
      | 1        | Test.allTheThings() T-Shirt (Red) | This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton. | 15.99 |
    And I click 'Checkout' button on Cart page
    And I set 'John' as first name on Checkout your information page
    And I set 'Smith' as last name on Checkout your information page
    And I set '11235' as zip on Checkout your information page
    And I click 'Continue' button on Checkout your information page
    And The following products are available on Checkout overview page:
      | quantity | product                           | description                                                                                                                                               | price |
      | 1        | Sauce Labs Bolt T-Shirt           | Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.           | 15.99 |
      | 1        | Test.allTheThings() T-Shirt (Red) | This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton. | 15.99 |
    And Item total is '31.98' on Checkout overview page
    And Tax is '2.56' on Checkout overview page
    And Total is '34.54' on Checkout overview page
    And I click 'Finish' button on Checkout overview page
    Then I am on Checkout complete page
