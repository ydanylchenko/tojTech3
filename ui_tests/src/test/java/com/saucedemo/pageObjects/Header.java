package com.saucedemo.pageObjects;

import com.saucedemo.ModernBasePage;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;

import static com.saucedemo.helpers.ElementsInteraction.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Header extends ModernBasePage {
    private static final By CART_SIZE_FIELD = By.className("shopping_cart_badge");
    private static final By CART_LINK = By.className("shopping_cart_link");

    public Header() {
    }

    public Header(boolean takeScreenShot) {
        super(takeScreenShot);
    }

    public void isPageOpened() {
        getWait().until(visibilityOfElementLocated(CART_LINK));
    }

    @And("^Cart contains '(.*)' item on Header$")
    public void verifyCartSize(String amount) {
        assertElementText(CART_SIZE_FIELD, amount);
    }

    @And("^I click on cart icon on Header$")
    public CartPage clickCart() {
        click(CART_LINK);
        return new CartPage(true);
    }
}
