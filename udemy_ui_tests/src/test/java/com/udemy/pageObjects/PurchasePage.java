package com.udemy.pageObjects;

import com.udemy.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.udemy.helpers.ElementsInteraction.click;
import static com.udemy.helpers.ElementsInteraction.getWait;

/**
 * Name of the page represents the web page name.
 * It extends the BasePage abstract class that implements common logic for page loading.
 * It also forces you to add isPageOpened method that is executed on creation of page object instance (object of class)
 */
public class PurchasePage extends BasePage {
    /**
     * This method is executed whenever new object of class is created.
     * It's done by calling the method in constructor of BasePage class
     */
    public void isPageOpened() {
//        Add verifications implemented using WebDriverWait that would be checking if the page is actually opened
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-purpose='cart-success-modal']")));
    }

//    other step definitions go here

    @And("I am on Purchase page")
    public void iAmOnPurchasePage() {
        isPageOpened();
    }

    @When("^I click on 'Go to Cart' button on Purchase page$")
    public CartPage iClickOnGoToCartButtonOnPurchasePage() {
        click(By.xpath("//button[@data-purpose=\"go-to-cart-button\"]"));
        return new CartPage();
    }
}
