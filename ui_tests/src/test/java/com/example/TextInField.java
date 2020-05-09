package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TextInField {

    @Test
    public void verifyTextInField() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://github.com/gogr/");
        String fullName = driver.findElement(By.xpath("//span[@itemprop=\"name\"]")).getText();
        System.out.println(fullName);
        String userName;
        userName = driver.findElement(By.className("p-nickname")).getText();
        System.out.println(userName);
        driver.quit();
    }

    @Test
    public void verifyTextInInput() {

    }
}