package com.udemy.pageObjects;

import com.udemy.BasePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.udemy.helpers.ElementsInteraction.click;
import static com.udemy.helpers.ElementsInteraction.getWait;

public class CoursePage extends BasePage {
    public void isPageOpened() {
        getWait().until(ExpectedConditions.titleContains(" | Udemy"));
        getWait().until(ExpectedConditions.urlContains("/course/"));
    }

    @Then("^I am on Course page$")
    public void iAmOnCoursePage() {
        isPageOpened();
    }

    @When("^I click on 'Add to cart' button on Course page$")
    public PurchasePage iClickOnAddToCartButtonOnCoursePage() {
        click(By.className("add-to-cart"));
        return new PurchasePage();
    }
}
