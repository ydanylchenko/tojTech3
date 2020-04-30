package com.jenkins.pageObjects;

import com.jenkins.CucumberHooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import static com.jenkins.CucumberHooks.getDriver;

public class GlobalSteps {
    @When("I open dashboard page")
    public void iOpenDashboardPage() {
        String baseUrl = "http://100.2.147.200:8080/";
        getDriver().get(baseUrl);
    }
    @When("sleep")
    public void sleep() throws InterruptedException {
        Thread.sleep(1000);
    }
}
