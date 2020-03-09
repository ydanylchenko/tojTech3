Feature: Purchase

  Background:
    Given I open start page
    And I set 'standard_user' as username on Sign in page
    And I set 'secret_sauce' as password on Sign in page
    And I click 'Sign In' button on Sign in page

  @purchase
  Scenario Outline: Purchase '<product>' product
    When I click 'ADD TO CART' button on '<product>' product on Products page
    Then Cart contains '1' item on Header
    And I click on cart icon on Header
    And The following products are available on Cart page:
      | quantity | product   | description   | price   |
      | 1        | <product> | <description> | <price> |
    And I click 'Checkout' button on Cart page
    And I set 'John' as first name on Checkout your information page
    And I set 'Smith' as last name on Checkout your information page
    And I set '11235' as zip on Checkout your information page
    And I click 'Continue' button on Checkout your information page
    And The following products are available on Checkout overview page:
      | quantity | product   | description   | price   |
      | 1        | <product> | <description> | <price> |
    And Item total is '<price>' on Checkout overview page
    And Tax is '<tax>' on Checkout overview page
    And Total is '<total>' on Checkout overview page
    And I click 'Finish' button on Checkout overview page
    Then I am on Checkout complete page

    Scenarios:
      | product                           | description                                                                                                                                                            | price | tax  | total |
      | Sauce Labs Backpack               | carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.                                 | 29.99 | 2.40 | 32.39 |
      | Sauce Labs Bike Light             | A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.        | 9.99  | 0.80 | 10.79 |
      | Sauce Labs Bolt T-Shirt           | Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.                        | 15.99 | 1.28 | 17.27 |
      | Sauce Labs Fleece Jacket          | It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office. | 49.99 | 4.00 | 53.99 |
      | Sauce Labs Onesie                 | Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.        | 7.99  | 0.64 | 8.63  |
      | Test.allTheThings() T-Shirt (Red) | This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.              | 15.99 | 1.28 | 17.27 |
