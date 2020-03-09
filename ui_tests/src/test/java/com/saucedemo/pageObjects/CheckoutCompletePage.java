package com.saucedemo.pageObjects;

import com.saucedemo.ModernBasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;

import static com.saucedemo.helpers.ElementsInteraction.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CheckoutCompletePage extends ModernBasePage {
    private static final By HEADER_FIELD = By.className("subheader");
    private static final By MESSAGE_HEADER_FIELD = By.className("complete-header");
    private static final By MESSAGE_FIELD = By.className("complete-text");

    public CheckoutCompletePage() {
    }

    public CheckoutCompletePage(boolean takeScreenShot) {
        super(takeScreenShot);
    }

    public void isPageOpened() {
        getWait().until(titleIs("Swag Labs"));
        assertElementText(HEADER_FIELD, "Finish");
        assertElementText(MESSAGE_HEADER_FIELD, "THANK YOU FOR YOUR ORDER");
        assertElementText(MESSAGE_FIELD, "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    @And("^I am on Checkout complete page$")
    public void verifyIsPageOpened() {
        waitForOpen();
    }
}
