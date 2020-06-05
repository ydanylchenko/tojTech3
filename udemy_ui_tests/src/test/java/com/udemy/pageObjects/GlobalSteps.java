package com.udemy.pageObjects;

import com.udemy.TestsConfig;
<<<<<<< HEAD
=======
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
>>>>>>> origin/master
import io.cucumber.java.en.When;

import static com.udemy.CucumberHooks.getDriver;

/**
 * This class contains global (common) steps that do not belong to any specific page
 * As well as all the entry points into the system (open a url)
 */
public class GlobalSteps {
<<<<<<< HEAD
    @When("I open start page")
    public ExamplePage iOpenDashboardPage() {
//        Base URL is read from config.properties file located in resources folder. Key is base.url
        String baseUrl = TestsConfig.getConfig().getBaseUrl();
        getDriver().get(baseUrl);
        return new ExamplePage();
=======
    @When("I open Start page")
    public StartPage iOpenDashboardPage() {
//        Base URL is read from config.properties file located in resources folder. Key is base.url
        String baseUrl = TestsConfig.getConfig().getBaseUrl();
        getDriver().get(baseUrl);
        return new StartPage();
>>>>>>> origin/master
    }

    @When("sleep")
    public void sleep() throws InterruptedException {
        Thread.sleep(1000);
    }
}
