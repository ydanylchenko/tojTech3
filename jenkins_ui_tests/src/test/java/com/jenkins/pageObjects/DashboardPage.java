package com.jenkins.pageObjects;

import com.jenkins.CucumberHooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class DashboardPage {
    @And("I click log in link on Dashboard page")
    public void iClickLogInLinkOnDashboardPage() {
        WebDriver driver = CucumberHooks.getDriver();
//        driver.
        By logInLinkLocator = By.tagName("b");
        WebElement loginLink = driver.findElement(logInLinkLocator);
//        loginLink.
        loginLink.click();
//        CucumberHooks.getDriver().findElement(By.tagName("b")).click();
    }

    @Then("I am signed in as THIS user on Dashboard page")
    public void iAmSignedInAsTHISUserOnDashboardPage() {
        String userName = CucumberHooks.getDriver().findElement(By.xpath("//a[contains(@href,'/user/')]")).getText();
        Assert.assertEquals(userName, "jenkinsUser", "Invalid user name");
    }
}
