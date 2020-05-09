package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestDropDown {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://newyork.craigslist.org/");
    }

    @AfterMethod
    public void tearDown() {
//        driver.quit();
    }

    @Test
    public void verifyTextInField() {
        System.out.println(driver.findElement(By.id("chlang")).getText());
//        driver.findElement(By.id("chlang")).click();
//        driver.findElement(By.xpath("//option[.='русский']")).click();
        new Select(driver.findElement(By.id("chlang"))).selectByIndex(4);
        new Select(driver.findElement(By.id("chlang"))).selectByVisibleText("русский");
        new Select(driver.findElement(By.id("chlang"))).selectByValue("?lang=it&setlang=1");
    }

}