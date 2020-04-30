package com.jenkins.pageObjects;

import com.jenkins.CucumberHooks;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;

public class LoginPage {
    @And("I set THIS as username on Login page")
    public void iSetTHISAsUsernameOnLoginPage() {
        CucumberHooks.getDriver().findElement(By.id("j_username")).sendKeys("jenkinsUser");
    }

    @And("I set THIS as password on Login page")
    public void iSetTHISAsPasswordOnLoginPage() {
        CucumberHooks.getDriver().findElement(By.xpath("//input[@placeholder=\"Password\"]")).sendKeys("123123");
    }

    @And("I click Sign in button on Login page")
    public void iClickSignInButtonOnLoginPage() {
        CucumberHooks.getDriver().findElement(By.name("Submit")).click();
    }
}
