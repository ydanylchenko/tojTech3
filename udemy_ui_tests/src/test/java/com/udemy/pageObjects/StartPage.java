package com.udemy.pageObjects;

import com.udemy.BasePage;
import com.udemy.CucumberHooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.udemy.helpers.ElementsInteraction.*;

/**
 * Name of the page represents the web page name.
 * It extends the BasePage abstract class that implements common logic for page loading.
 * It also forces you to add isPageOpened method that is executed on creation of page object instance (object of class)
 */
public class StartPage extends BasePage {
    /**
     * This method is executed whenever new object of class is created.
     * It's done by calling the method in constructor of BasePage class
     */
    public void isPageOpened() {
//        Add verifications implemented using WebDriverWait that would be checking if the page is actually opened
        getWait().until(ExpectedConditions.titleIs("Online Courses - Learn Anything, On Your Schedule | Udemy"));
    }

//    other step definitions go here

    @When("^I set '(.*)' as search criteria on Start page$")
    public void iSetJavaAsSearchCriteriaOnStartPage(String searchCriteria) {
        sendKeys(By.id("header-search-field"), searchCriteria);
    }

    @And("^I click on 'search' button on Start page$")
    public SearchResultsPage iClickOnSearchButtonOnStartPage() {
        click(By.xpath("//input[@id=\"header-search-field\"]/..//button"));
        return new SearchResultsPage();
    }
}
