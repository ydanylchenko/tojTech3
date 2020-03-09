package com.saucedemo.pageObjects;

import com.saucedemo.ModernBasePage;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;

import static com.saucedemo.helpers.ElementsInteraction.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class SignInPage extends ModernBasePage {
    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.xpath("//input[@value='LOGIN']");
    private static final By LOGO_FIELD = By.className("login_logo");
    private static final By ERROR_FIELD = By.xpath("//h3[@data-test='error']");

    public SignInPage() {
    }

    public SignInPage(boolean takeScreenShot) {
        super(takeScreenShot);
    }

    public void isPageOpened() {
        getWait().until(titleIs("Swag Labs"));
        getWait().until(visibilityOfElementLocated(LOGO_FIELD));
    }

    @And("^I am on Sign in page$")
    public void verifyIsPageOpened() {
        waitForOpen();
    }

    @And("^I set '(.*)' as username on Sign in page$")
    public void setEmail(String username) {
        sendKeys(USERNAME_INPUT, username);
    }

    @And("^I set '(.*)' as password on Sign in page$")
    public void setPassword(String password) {
        sendKeys(PASSWORD_INPUT, password);
    }

    @And("^I click 'Sign In' button on Sign in page$")
    public ProductsPage clickSignInButton() {
        click(LOGIN_BUTTON);
        new Header(true);
        return new ProductsPage(true);
    }

    @And("^I click 'Sign In' button on Sign in page expecting failure$")
    public SignInPage clickSignInButtonExpectingFailure() {
        click(LOGIN_BUTTON);
        return new SignInPage(true);
    }

    @And("^Error message '(.*)' is available on Sign in page$")
    public void verifyErrorMessage(String message) {
        assertElementText(ERROR_FIELD, message);
    }
}
