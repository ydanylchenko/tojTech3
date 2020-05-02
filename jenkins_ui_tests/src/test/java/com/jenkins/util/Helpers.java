package com.jenkins.util;

import com.jenkins.CucumberHooks;
import com.jenkins.selenium.WaitForAjaxCalls;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.jenkins.CucumberHooks.getDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Helpers {
    public static WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), 15);
    }

    public static void typeTextOldSchool(By locator, String text) {
        getDriver().findElement(locator).sendKeys(text);
    }

    public static void click(By elementLocator) {
        getWait().until(ExpectedConditions.elementToBeClickable(elementLocator)).click();
    }

    public static void typeText(By inputElementLocator, String text) {
        getWait().until(visibilityOfElementLocated(inputElementLocator)).sendKeys(text);
        new WaitForAjaxCalls(getDriver()).checkPendingRequests();
        getWait().until(ExpectedConditions.textToBePresentInElementValue(inputElementLocator, text));
    }

}
