package com.example.selenium;

import com.example.context.Context;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static com.example.selenium.SeleniumConfig.getConfig;

/**
 * Base class for web tests. It contains web driver {@link WebDriver} instance, used in all tests.
 * All communications with driver should be done through this class
 */
public class WebDriverFactory {
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverFactory.class);

    /**
     * Main method of class - it initialize driver and starts browser.
     */
    public static WebDriver startBrowser(Context context) throws MalformedURLException {
        Browser browser = Browser.getByName(getConfig().getBrowser());
        WebDriver webDriver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability("ignore-certificate-errors", true);
        if (Browser.CHROME.equals(browser)) {
//            Enable logging
            LoggingPreferences logs = new LoggingPreferences();
            logs.enable(LogType.DRIVER, Level.FINE);
            capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
        }
        if (getConfig().getSeleniumBrowserIsGrid()) {
            capabilities.setBrowserName(getConfig().getBrowser());
            capabilities.setVersion(getConfig().getBrowserVersion());
            capabilities.setPlatform(Platform.valueOf(getConfig().getBrowserOs()));
            capabilities.setCapability("name", context.getScenarioName());
            capabilities.setCapability("screenResolution", "1500x2000x24");
            capabilities.setCapability("enableVNC", getConfig().getSeleniumBrowserEnableVNC());
            capabilities.setCapability("enableVideo", getConfig().getSeleniumBrowserEnableVideo());
            capabilities.setCapability("timeZone", getConfig().getBrowserTimeZone());
            if (Browser.CHROME.equals(browser)) {
                capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions(context));
            }
            URL gridUrl = new URL(getConfig().getSeleniumGridUrl() + "/wd/hub");
            RemoteWebDriver remoteWebDriver;
            try {
                remoteWebDriver = new RemoteWebDriver(gridUrl, capabilities);
            } catch (WebDriverException e) {
                e.printStackTrace();
                LOG.error("Browser startup failure. Retrying");
                remoteWebDriver = new RemoteWebDriver(gridUrl, capabilities);
            }
            remoteWebDriver.setFileDetector(new LocalFileDetector());
            webDriver = remoteWebDriver;
            webDriver.manage().window().maximize();
        } else {
            switch (Objects.requireNonNull(browser)) {
                case FIREFOX:
                    webDriver = new FirefoxDriver(new FirefoxOptions().merge(capabilities));
                    break;
                case CHROME:
                    webDriver = new ChromeDriver(getChromeOptions(context).merge(capabilities));
                    break;
                default:
                    throw new IllegalStateException(browser.getBrowserName() + " is unsupported browser");
            }
        }
        webDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        return webDriver;
    }

    private static ChromeOptions getChromeOptions(Context context) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1400,1400");
        options.setExperimentalOption("w3c", false); // For logging purposes
        if (context.isMobile()) {
            Map<String, String> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", "iPhone 6");
            options.setExperimentalOption("mobileEmulation", mobileEmulation);
        }
        options.setExperimentalOption("prefs", new HashMap<String, Object>() {
            {
                put("profile.default_content_settings.popups", 0);
                put("download.prompt_for_download", false);
                put("download.directory_upgrade", true);
                put("safebrowsing.enabled", false);
                put("plugins.always_open_pdf_externally", true);
                put("plugins.plugins_disabled", new ArrayList<String>() {
                    {
                        add("Chrome PDF Viewer");
                    }
                });
            }
        });
        return options;
    }

    /**
     * Finishes browser
     */
    public static void finishBrowser(Context context, WebDriver driver) throws UnknownHostException {
        if (getConfig().getSeleniumBrowserIsGrid() && getConfig().getSeleniumBrowserEnableVideo()) {
            LOG.info("Saving video url to context");
            String url = String.format("%s/video/%s.mp4",
                    getConfig().getSeleniumGridUrl(),
                    ((RemoteWebDriver) driver).getSessionId());
            if (url.contains("localhost")) {
                url = url.replace("localhost", InetAddress.getLocalHost().getHostAddress());
            }
            context.getDataStore().put("video", url);
        }
//        In case if browser timed out and killed by docker
        try {
            driver.quit();
        } catch (Exception e) {
            LOG.warn(e.getMessage());
        }
    }

    public static void captureScreenshot(Context context, WebDriver driver, String pageName) {
        String screenName = pageName == null ? "blankLandingPage" : pageName;
        String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss-SSS"));
        String screenshotName = String.format("%s_%s.png", timestamp, screenName);
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        context.getScreenshots().put(screenshotName, screenshot);
    }
}
