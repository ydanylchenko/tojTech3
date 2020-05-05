package com.udemy;

import com.udemy.selenium.WaitForAjaxCalls;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.udemy.CucumberHooks.getContext;
import static com.udemy.CucumberHooks.getDriver;

/**
 * This is the main class for page objects
 */
public abstract class BasePage {
    private static final Logger LOG = LoggerFactory.getLogger(BasePage.class);

    /**
     * Checks if page opened
     * This method is executed whenever new object of class is created.
     * It's done by calling the method in constructor of BasePage class
     */
    public abstract void isPageOpened();

    /**
     * This constructor is called whenever a page object of a class that extends base page is created (instantiated)
     * by calling waitForOpen() method that is calling Page specific isPageOpened() method
     */
    public BasePage() {
        String pageName = this.getClass().getSimpleName();
        getContext().setPageName(pageName);
        LOG.debug("open {}", pageName);
        waitForOpen();
    }

    /**
     * Waiting for page opening
     */
    protected void waitForOpen() {
        LOG.info("{} page loading started", this.getClass().getSimpleName());
        long currentTime = System.currentTimeMillis();
        new WaitForAjaxCalls(getDriver()).checkPendingRequests();
        try {
            isPageOpened();
        } catch (WebDriverException e) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " page was not opened!", e.getCause());
        }
        LOG.info(String.format("%s page loaded in %.1f s on %s url",
                this.getClass().getSimpleName(),
                (float) (System.currentTimeMillis() - currentTime) / 1000,
                getDriver().getCurrentUrl()));
    }
}
