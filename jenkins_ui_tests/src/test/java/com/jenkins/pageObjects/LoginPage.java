package com.jenkins.pageObjects;

import com.jenkins.BasePage;
import com.jenkins.CucumberHooks;
import com.jenkins.selenium.WaitForAjaxCalls;
import com.jenkins.util.Helpers;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.jenkins.util.Helpers.*;

public class LoginPage extends BasePage {
    //    public LoginPage() {
//        new WaitForAjaxCalls(CucumberHooks.getDriver()).checkPendingRequests();
//        Helpers.getWait().until(ExpectedConditions.titleIs("Sign in [Jenkins]"));
//        Helpers.getWait().until(ExpectedConditions.urlContains("login"));
//        Helpers.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("logo")));
//    }
    public void isPageOpened() {
        Helpers.getWait().until(ExpectedConditions.titleIs("Sign in [Jenkins]"));
        Helpers.getWait().until(ExpectedConditions.urlContains("login"));
        Helpers.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.className("logo")));
    }

    //    This is cucumber expression https://cucumber.io/docs/cucumber/cucumber-expressions/
    @And("I set {} as username on Login page")
    public void iSetTHISAsUsernameOnLoginPage(String username) {
//        CucumberHooks.getDriver().findElement(By.id("j_username")).sendKeys(username);
        typeText(By.id("j_username"), username);
    }

    //    This is rexeg:
    @And("^I set (.*) as password on Login page$")
    public void iSetTHISAsPasswordOnLoginPage(String password) {
//        CucumberHooks.getDriver().findElement(By.xpath("//input[@placeholder=\"Password\"]")).sendKeys(password);
        typeText(By.xpath("//input[@placeholder=\"Password\"]"), password);
    }

    @And("I click Sign in button on Login page")
    public DashboardPage iClickSignInButtonOnLoginPage() {
//        CucumberHooks.getDriver().findElement(By.name("Submit")).click();
        click(By.name("Submit"));
//        DashboardPage dashboardPage = new DashboardPage();
//        return dashboardPage;
        return new DashboardPage();
    }
}
