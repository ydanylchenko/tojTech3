package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCheckbox {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://github.com/join/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifyTextInField() {
        Assert.assertTrue(driver.findElement(By.id("all_emails")).isSelected());
//        In order ot click on element
        driver.findElement(By.id("all_emails")).click();
//        in order to get the crrent state of checkbox: true if checked and false if unchecked
        System.out.println(driver.findElement(By.id("all_emails")).isSelected());
        Assert.assertFalse(driver.findElement(By.id("all_emails")).isSelected());
        driver.findElement(By.id("all_emails")).click();
        System.out.println(driver.findElement(By.id("all_emails")).isSelected());
        Assert.assertTrue(driver.findElement(By.id("all_emails")).isSelected());
    }

}