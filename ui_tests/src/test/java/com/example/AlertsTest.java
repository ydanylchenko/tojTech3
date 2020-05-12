package com.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class AlertsTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_confirm");
        driver.switchTo().frame("iframeResult");
        driver.findElement(By.xpath("//button[.='Try it']")).click();
        new WebDriverWait(driver, 15).until(ExpectedConditions.alertIsPresent());
    }

    @AfterMethod
    public void tearDown() {
        driver.switchTo().defaultContent();
        driver.quit();
    }

    @Test
    public void w3cAlertAcceptTest() {
        Alert displayedAlert = driver.switchTo().alert();
        System.out.println(displayedAlert.getText());
        assertEquals(displayedAlert.getText(), "Press a button!");
        displayedAlert.accept();
    }

    @Test
    public void w3cAlertDismissTest() {
        Alert displayedAlert = driver.switchTo().alert();
        System.out.println(displayedAlert.getText());
        assertEquals(displayedAlert.getText(), "Press a button!");
        displayedAlert.dismiss();
    }
}
