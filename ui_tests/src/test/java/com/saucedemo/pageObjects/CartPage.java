package com.saucedemo.pageObjects;

import com.saucedemo.BasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.saucedemo.CucumberHooks.getDriver;
import static com.saucedemo.helpers.ElementsInteraction.*;
import static com.saucedemo.pageObjects.GlobalSteps.getFormattedItems;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.testng.Assert.assertEquals;

public class CartPage extends BasePage {
    private static final Logger LOG = LoggerFactory.getLogger(CartPage.class);
    private static final By CHECKOUT_BUTTON = By.className("checkout_button");
    private static final By HEADER_FIELD = By.className("subheader");
    private static final By CART_ITEM_CONTAINER = By.className("cart_item");
    private static final By CART_ITEM_QUANTITY_FIELD = By.className("cart_quantity");
    private static final By CART_ITEM_ITEM_NAME_FIELD = By.className("inventory_item_name");
    private static final By CART_ITEM_DESCRIPTION_FIELD = By.className("inventory_item_desc");
    private static final By CART_ITEM_PRICE_FIELD = By.className("inventory_item_price");

    public CartPage() {
    }

    public CartPage(boolean takeScreenShot) {
        super(takeScreenShot);
    }

    public void isPageOpened() {
        getWait().until(titleIs("Swag Labs"));
        assertElementText(HEADER_FIELD, "Your Cart");
    }

    @Given("^I am on Cart page$")
    public void verifyIsPageOpened() {
        waitForOpen();
    }

    private static By getCardFieldLocator(String templateFieldName) {
        switch (templateFieldName) {
            case "quantity":
                return CART_ITEM_QUANTITY_FIELD;
            case "product":
                return CART_ITEM_ITEM_NAME_FIELD;
            case "description":
                return CART_ITEM_DESCRIPTION_FIELD;
            case "price":
                return CART_ITEM_PRICE_FIELD;
            default:
                throw new IllegalArgumentException("Unsupported field name " + templateFieldName);
        }
    }

    @When("^The following products are (available|not available) on Cart page:$")
    public void verifyCart(String availability, DataTable dataTable) {
        List<Map<String, String>> products = dataTable.asMaps(String.class, String.class);
        List<WebElement> cartItems = getDriver().findElements(CART_ITEM_CONTAINER);
        for (Map<String, String> product : products) {
            List<WebElement> rows = getFilteredCartItems(product);
            String actualAvailability = rows.isEmpty() ? "not available" : "available";
            assertEquals(actualAvailability, availability,
                    String.format("'%s' should be '%s' in\n%s", product, availability, getFormattedItems(cartItems)));
        }
    }

    private List<WebElement> getFilteredCartItems(final Map<String, String> product) {
        List<WebElement> cartItems = getDriver().findElements(CART_ITEM_CONTAINER);
        LOG.debug("Original cart items ({}):\n{}", cartItems.size(), getFormattedItems(cartItems));
        List<WebElement> rows = new ArrayList<>(cartItems);
        String lastCheckedColumn = null;
        for (String property : product.keySet()) {
            By locator = getCardFieldLocator(property);
            String value = product.get(property);
            if (rows.isEmpty()) {
                LOG.info("Zero results after filtration by {}", lastCheckedColumn);
                break;
            } else {
                rows = rows.stream()
                        .filter(item -> (item.findElement(locator).getText()).equals(value))
                        .collect(Collectors.toList());
            }
            LOG.info("{} remains after filtration by '{}'='{}':\n{}", rows.size(), property, value,
                    getFormattedItems(rows));
            lastCheckedColumn = property;
        }
        return rows;
    }

    @When("^I click 'Checkout' button on Cart page$")
    public CheckoutYourInformationPage clickCheckout() {
        click(CHECKOUT_BUTTON);
        return new CheckoutYourInformationPage(true);
    }
}
