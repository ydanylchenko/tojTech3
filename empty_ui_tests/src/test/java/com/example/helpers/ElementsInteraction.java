package com.example.helpers;

import com.example.selenium.SeleniumConfig;
import com.example.selenium.WaitForAjaxCalls;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.example.CucumberHooks.getDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * Select element from active search results.
 */
public final class ElementsInteraction {
    private static final int TIMEOUT = SeleniumConfig.getConfig().getPageLoadingRetriesCount();

    private ElementsInteraction() {
    }

    public static WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), TIMEOUT);
    }

    public static void click(By elementLocator) {
        getWait().until(elementToBeClickable(elementLocator)).click();
    }

    public static void sendKeys(By inputElementLocator, String text) {
        getWait().until(visibilityOfElementLocated(inputElementLocator)).sendKeys(text);
        new WaitForAjaxCalls(getDriver()).checkPendingRequests();
        assertElementValue(inputElementLocator, text);
    }

    public static void assertElementText(By elementLocator, String text) {
        getWait().ignoring(StaleElementReferenceException.class)
                .until(textToBe(elementLocator, text));
    }

    public static void assertElementValue(By elementLocator, String text) {
        getWait().ignoring(StaleElementReferenceException.class)
                .until(textToBePresentInElementValue(elementLocator, text));
    }

    public static By getLocator(String elementLocatorTemplate, String... dynamicLocatorParts) {
        return By.xpath(String.format(elementLocatorTemplate, dynamicLocatorParts));
    }
}
