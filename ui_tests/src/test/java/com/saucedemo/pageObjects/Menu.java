package com.saucedemo.pageObjects;

import com.saucedemo.BasePage;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;

import static com.saucedemo.helpers.ElementsInteraction.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Menu extends BasePage {
    private static final By MENU_CONTAINER = By.className("bm-menu");
    private static final By LOGOUT_LINK = By.id("logout_sidebar_link");

    public Menu() {
    }

    public Menu(boolean takeScreenShot) {
        super(takeScreenShot);
    }

    public void isPageOpened() {
        getWait().until(visibilityOfElementLocated(MENU_CONTAINER));
    }

    @And("^I click 'Logout' link on Menu$")
    public SignInPage clickLogout() {
        click(LOGOUT_LINK);
        return new SignInPage(true);
    }
}
