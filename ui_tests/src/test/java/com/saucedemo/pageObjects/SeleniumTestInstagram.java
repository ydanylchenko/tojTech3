package com.saucedemo.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTestInstagram {
    public static void main(String[] args) throws InterruptedException {
//        To Start browser
        WebDriver driver;
        driver = new ChromeDriver();
        Thread.sleep(2000);
//        To open a URL
        driver.get("https://www.instagram.com/");
        Thread.sleep(2000);
//        To click on Sing up button
        driver.findElement(By.xpath("//span[text()='Sign up']")).click();
        Thread.sleep(2000);
//        To type "test@example.com" text into Mobile Number or Email input
        driver.findElement(By.name("emailOrPhone")).sendKeys("test@example.com");
        Thread.sleep(2000);
//        To click on element and type FirstName LastName
        driver.findElement(By.name("fullName")).sendKeys("FirstName LastName");
        Thread.sleep(2000);
//        To type someUsername in username input
        driver.findElement(By.name("username")).sendKeys("someUsername");
        Thread.sleep(2000);
//        To type P@ssw0rd text in input
        driver.findElement(By.name("password")).sendKeys("P@ssw0rd");
//        To click Sign up button
        driver.findElement(By.tagName("button")).click();
//        To close browser
        driver.quit();

    }
}
