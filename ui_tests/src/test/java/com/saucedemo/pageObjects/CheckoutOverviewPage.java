package com.saucedemo.pageObjects;

import com.saucedemo.ModernBasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.saucedemo.CucumberHooks.getContext;
import static com.saucedemo.CucumberHooks.getDriver;
import static com.saucedemo.helpers.ElementsInteraction.*;
import static com.saucedemo.pageObjects.GlobalSteps.getFormattedItems;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.testng.Assert.assertEquals;

public class CheckoutOverviewPage extends ModernBasePage {
    private static final Logger LOG = LoggerFactory.getLogger(CheckoutOverviewPage.class);
    private static final By FINISH_BUTTON = By.linkText("FINISH");
    private static final By HEADER_FIELD = By.className("subheader");
    private static final By SUBTOTAL_FIELD = By.className("summary_subtotal_label");
    private static final By TAX_FIELD = By.className("summary_tax_label");
    private static final By TOTAL_FIELD = By.className("summary_total_label");
    private static final By CART_ITEM_CONTAINER = By.className("cart_item");
    private static final By CART_ITEM_QUANTITY_FIELD = By.className("summary_quantity");
    private static final By CART_ITEM_ITEM_NAME_FIELD = By.className("inventory_item_name");
    private static final By CART_ITEM_DESCRIPTION_FIELD = By.className("inventory_item_desc");
    private static final By CART_ITEM_PRICE_FIELD = By.className("inventory_item_price");

    public CheckoutOverviewPage() {
    }

    public CheckoutOverviewPage(boolean takeScreenShot) {
        super(takeScreenShot);
    }

    public void isPageOpened() {
        getWait().until(titleIs("Swag Labs"));
        assertElementText(HEADER_FIELD, "Checkout: Overview");
    }

    @And("^I am on Checkout overview page$")
    public void verifyIsPageOpened() {
        waitForOpen();
    }

    @And("^I click 'Finish' button on Checkout overview page$")
    public CheckoutCompletePage clickFinish() {
        click(FINISH_BUTTON);
        return new CheckoutCompletePage(true);
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

    @When("^The following products are (available|not available) on Checkout overview page:$")
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
                        .filter(item -> (item.findElement(locator).getText()).contains(value))
                        .collect(Collectors.toList());
            }
            LOG.info("{} remains after filtration by '{}'='{}':\n{}", rows.size(), property, value,
                    getFormattedItems(rows));
            lastCheckedColumn = property;
        }
        return rows;
    }

    @And("^Item total is '(.*)' on Checkout overview page$")
    public void verifyItemTotal(String itemTotal) {
        assertElementText(SUBTOTAL_FIELD, "Item total: $" + itemTotal);
    }

    @And("^Tax is '(.*)' on Checkout overview page$")
    public void verifyTax(String tax) {
        assertElementText(TAX_FIELD, "Tax: $" + tax);
    }

    @And("^Total is '(.*)' on Checkout overview page$")
    public void verifyTotal(String total) {
        assertElementText(TOTAL_FIELD, "Total: $" + total);
    }
}
