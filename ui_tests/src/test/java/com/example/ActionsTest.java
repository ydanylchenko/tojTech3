package com.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class ActionsTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void actionsDragAndDropTest() throws InterruptedException {
        driver.get("https://kleki.com/");
        Thread.sleep(2000);
        Actions action = new Actions(driver);
        action.moveByOffset(100, 100);
        action.clickAndHold();
        action.moveByOffset(200, 200);
        action.release();
//        The action is now created, we can perform it
        action.perform();
        Thread.sleep(5000);
    }

    @Test
    public void actionsDragAndDropTestChained() throws InterruptedException {
        driver.get("https://kleki.com/");
        Thread.sleep(2000);
        Actions action = new Actions(driver);
        action.moveByOffset(100, 100).clickAndHold().moveByOffset(200, 200).release().perform();
    //        The action is now created, we can perform it

        Thread.sleep(5000);
    }

    @Test
    public void actionHoverTest() throws InterruptedException {
        driver.get("https://www.theladders.com/");
        Thread.sleep(5000);
        new Actions(driver).moveToElement(driver.findElement(By.id("guest-jobs"))).perform();
        new Actions(driver).moveToElement(driver.findElement(By.className("companies"))).perform();
        Thread.sleep(5000);
    }
}
