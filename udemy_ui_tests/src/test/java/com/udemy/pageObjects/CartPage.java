package com.udemy.pageObjects;

import com.udemy.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.udemy.helpers.ElementsInteraction.assertElementText;
import static com.udemy.helpers.ElementsInteraction.getWait;

/**
 * Name of the page represents the web page name.
 * It extends the BasePage abstract class that implements common logic for page loading.
 * It also forces you to add isPageOpened method that is executed on creation of page object instance (object of class)
 */
public class CartPage extends BasePage {
    /**
     * This method is executed whenever new object of class is created.
     * It's done by calling the method in constructor of BasePage class
     */
    public void isPageOpened() {
//        Add verifications implemented using WebDriverWait that would be checking if the page is actually opened
        getWait().until(ExpectedConditions.titleIs("Online Courses - Anytime, Anywhere | Udemy"));
        getWait().until(ExpectedConditions.urlContains("/cart/"));
    }
//    other step definitions go here


    @Then("I am on Cart page")
    public void iAmOnCartPage() {
        isPageOpened();
    }

    @Then("^Cart contains '(.*)' course on Cart page$")
    public void cartContainsSeleniumWebDriverWithJavaForBeginnersCourseOnCartPage(String course) {
        By courseName = By.xpath(String.format("//div[@data-purpose='cart-thumbnail']/..//div[.='%s']", course));
        getWait().until(ExpectedConditions.visibilityOfElementLocated(courseName));
    }

    @And("^Cart subtotal is '(.*)' on cart page$")
    public void cartSubtotalIsOnCartPage(String price) {
        By priceLocator = By.xpath("//div[@data-purpose='total-price']//div[@data-purpose='course-price-text']/span/span");
        assertElementText(priceLocator, price);
    }
}
