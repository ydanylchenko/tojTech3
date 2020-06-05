package com.udemy.pageObjects;

import com.udemy.BasePage;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.udemy.helpers.ElementsInteraction.click;
import static com.udemy.helpers.ElementsInteraction.getWait;

/**
 * Name of the page represents the web page name.
 * It extends the BasePage abstract class that implements common logic for page loading.
 * It also forces you to add isPageOpened method that is executed on creation of page object instance (object of class)
 */
public class SearchResultsPage extends BasePage {
    /**
     * This method is executed whenever new object of class is created.
     * It's done by calling the method in constructor of BasePage class
     */
    public void isPageOpened() {
//        Add verifications implemented using WebDriverWait that would be checking if the page is actually opened
        getWait().until(ExpectedConditions.titleIs("Online Courses - Anytime, Anywhere | Udemy"));
        getWait().until(ExpectedConditions.urlContains("/courses/search"));
    }

    @And("^I am on Search results page$")
    public void iAmOnSearchResultsPage() {
        isPageOpened();
    }


    @And("^I click on '(.*)' course name on Search results page$")
    public CoursePage iClickOnCourseNameOnSearchResultsPage(String courseName) {
//        click(By.xpath("//div[.='" + courseName + "']"));
        click(By.xpath(String.format("//div[.='%s']", courseName)));
        return new CoursePage();
    }
//    other step definitions go here
}
