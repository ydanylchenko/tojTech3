package com.saucedemo;

import com.saucedemo.selenium.SeleniumConfig;
import com.saucedemo.selenium.WaitForAjaxCalls;
import com.saucedemo.selenium.WebDriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.saucedemo.CucumberHooks.getContext;
import static com.saucedemo.CucumberHooks.getDriver;

/**
 * This is the main class for page objects
 */
public abstract class ModernBasePage {
    private static final Logger LOG = LoggerFactory.getLogger(ModernBasePage.class);

    /**
     * checks is page opened
     */
    public abstract void isPageOpened();

    /**
     * This constructor is just for the internal Cucumber use as on first call of a step from any page
     * object. Use constructor with boolean parameter
     */
    public ModernBasePage() {
    }

    public ModernBasePage(boolean captureScreenShot) {
        String pageName = this.getClass().getSimpleName();
        getContext().setPageName(pageName);
        LOG.debug("open {}", pageName);
        waitForOpen();
        if (captureScreenShot && SeleniumConfig.getConfig().getEnableScreenShotsCapture()) {
            WebDriverFactory.captureScreenshot(getContext(), getDriver(), pageName);
        }
    }

    /**
     * Waiting for page opening
     */
    protected void waitForOpen() {
        LOG.info("{} page loading started", this.getClass().getSimpleName());
        long currentTime = System.currentTimeMillis();
        new WaitForAjaxCalls(getDriver()).checkPendingRequests();
        isPageOpened();
        LOG.info(String.format("%s page loaded in %.1f s on %s url",
                this.getClass().getSimpleName(),
                (float) (System.currentTimeMillis() - currentTime) / 1000,
                getDriver().getCurrentUrl()));
    }
}
