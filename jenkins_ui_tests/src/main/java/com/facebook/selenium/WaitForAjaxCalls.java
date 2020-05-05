package com.facebook.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wait till amount of pending requests will be zero.
 */
public final class WaitForAjaxCalls {
    private static final Logger LOG = LoggerFactory.getLogger(WaitForAjaxCalls.class);
    private JavascriptExecutor driver;
    /**
     * As per default website connection timeout in Chrome
     */
    private static final long TIMEOUT = 5 * 60 * 1000;
    private static final String GET_PENDING_REQUESTS_COUNT = "return window.openHTTPs";
    private static final String MONKEY_PATCH = "  (function() {" +
            "var oldOpen = XMLHttpRequest.prototype.open;" +
            "window.openHTTPs = 0;" +
            "XMLHttpRequest.prototype.open = function(method, url, async, user, pass) {" +
            "window.openHTTPs++;" +
            "this.addEventListener('readystatechange', function() {" +
            "if(this.readyState == 4) {" +
            "window.openHTTPs--;" +
            "}" +
            "}, false);" +
            "oldOpen.call(this, method, url, async, user, pass);" +
            "}" +
            "})();";

    public WaitForAjaxCalls(WebDriver driver) {
        this.driver = (JavascriptExecutor) driver;
    }

    public void checkPendingRequests() {
        long startTime = System.currentTimeMillis();
        while (getPendingRequestsAmount() != 0L) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime > TIMEOUT) {
                throw new IllegalStateException(String.format("%s pending requests remain after %d seconds",
                        getPendingRequestsAmount(), elapsedTime));
            }
        }
    }

    private Long getPendingRequestsAmount() {
        if (driver.executeScript(GET_PENDING_REQUESTS_COUNT) == null) {
            driver.executeScript(MONKEY_PATCH);
        }
        Object pendingRequests = driver.executeScript(GET_PENDING_REQUESTS_COUNT);
        if (pendingRequests == null) {
            LOG.warn("Could not retrieve amount of pending requests");
            return 0L;
        } else {
            return (Long) pendingRequests;
        }
    }
}
