package com.facebook;

import com.facebook.context.Context;
import com.facebook.selenium.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;

import static com.facebook.selenium.SeleniumConfig.getConfig;

public class CucumberHooks {
    private static final Logger LOG = LoggerFactory.getLogger(CucumberHooks.class);
    private static ThreadLocal<Context> context = ThreadLocal.withInitial(Context::new);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal();

    /**
     * Getting of pre-configured {@link WebDriver} instance.
     *
     * @return webdriver object
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    public static Context getContext() {
        return context.get();
    }

    @Before
    public void beforeTest(Scenario scenario) throws InterruptedException, MalformedURLException {
        LOG.info("Cucumber startup hooks are executed");
        if (scenario == null)
            throw new IllegalStateException("Scenario must not be null");
//        Add scenario name to user data for screenshot folder creation
        getContext().setFeatureName(scenario.getUri().toString().replaceAll(".*/", ""));
        getContext().setScenarioName(scenario.getName());
        getContext().setIsMobile(scenario.getSourceTagNames().contains("@mobile"));
        LOG.info("Starting WebDriver");
        driver.set(WebDriverFactory.startBrowser(getContext()));
        LOG.info("WebDriver ready? {}", getDriver().getCurrentUrl().isEmpty());
        {
            String threadName = Thread.currentThread().getName();
            LOG.info("Thread name: " + threadName);
            if (!threadName.replaceAll("\\D+", "").isEmpty()) {
                int sleepTime = Integer.parseInt(threadName.replaceAll("\\D+", "")) * 200;
                LOG.info("Sleeping for " + sleepTime + " ms as per thread identifier");
                Thread.sleep(sleepTime);
            }
        }
        LOG.info("Cucumber startup hooks execution is done");
    }

    /**
     * Method for screenshot taking. It is empty now, because you could save your screenshot as you want.
     * This method calls in tests listeners on test fail
     */
    @After
    public void afterTest(Scenario scenario) throws IOException {
        LOG.info("Cucumber shutdown hooks are executed");
        try {
            if (scenario != null && scenario.isFailed()) {
                LOG.error("URL on witch test was failed {}", getDriver().getCurrentUrl());
                String pageName = getContext().getPageName();
                {
                    LOG.info("Embedding screenshot");
                    WebDriverFactory.captureScreenshot(getContext(), getDriver(), pageName);
                    getContext().storeCapturedScreenshot(true);
                    scenario.embed(getContext().getScreenshots().lastEntry().getValue(),
                            "image/png",
                            getContext().getScreenshots().lastEntry().getKey());
                    LOG.info("Screenshot is embedded");
                }
                {
                    LOG.info("Logging network errors");
                    getDriver()
                            .manage()
                            .logs()
                            .get("driver")
                            .getAll()
                            .stream()
                            .filter(logEntry -> logEntry.toJson().get("message").toString().contains("\"error\""))
                            .forEach(entry -> LOG.error("Console Error:\n{}", entry));
                    LOG.info("Network errors logged");
                }
            }
            if (getConfig().getEnableScreenShotsCapture()) {
                LOG.info("Saving all captured screenshots");
                getContext().storeCapturedScreenshot();
            }
        } finally {
            WebDriverFactory.finishBrowser(getContext(), getDriver());
            driver.remove();
            context.remove();
        }
        LOG.info("Cucumber shutdown hooks execution is done");
    }
}
