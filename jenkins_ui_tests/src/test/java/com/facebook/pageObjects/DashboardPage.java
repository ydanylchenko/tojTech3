package com.facebook.pageObjects;

import com.facebook.BasePage;
import com.facebook.CucumberHooks;
import com.facebook.selenium.WaitForAjaxCalls;
import com.facebook.util.Helpers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.facebook.util.Helpers.*;

public class DashboardPage extends BasePage {
    public void isPageOpened() {
        new WaitForAjaxCalls(CucumberHooks.getDriver()).checkPendingRequests();
        Helpers.getWait().until(ExpectedConditions.titleIs("Dashboard [Jenkins]"));
        Helpers.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("projectstatus")));
    }

    @And("I click log in link on Dashboard page")
    public LoginPage iClickLogInLinkOnDashboardPage() {
//        {
//            WebDriver driver = CucumberHooks.getDriver();
////        driver.
//            By logInLinkLocator = By.tagName("b");
//            WebElement loginLink = driver.findElement(logInLinkLocator);
////        loginLink.
//            loginLink.click();
//        }
        click(By.tagName("b"));
//        CucumberHooks.getDriver().findElement(By.tagName("b")).click();
        return new LoginPage();
    }

    @And("^I set (.*) as serach critirea on dashboard page$")
    public void setSearchCrit(String searchCrit) {
//        CucumberHooks.getDriver().findElement(By.xpath("//input[@placeholder=\"Password\"]")).sendKeys(password);
        typeText(By.id("search-box"), searchCrit);
    }

    @Then("^I am signed in as (.*) user on Dashboard page$")
    public void iAmSignedInAsTHISUserOnDashboardPage(String name) {
//        String userName = CucumberHooks.getDriver().findElement(By.xpath("//a[contains(@href,'/user/')]")).getText();
//        Assert.assertEquals(userName, name, "Invalid user name");
        getWait().until(ExpectedConditions.textToBe(By.xpath("//a[contains(@href,'/user/')]"), name));
    }
}
