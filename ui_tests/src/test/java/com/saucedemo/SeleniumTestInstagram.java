package com.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTestInstagram {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println("Hello World");
//        To Start browser
        WebDriver driver;
        driver = new ChromeDriver();
        Thread.sleep(2000);
//        To open a URL
        driver.get("https://www.instagram.com/");
        Thread.sleep(2000);
//        Click Sign up button
        driver.findElement(By.linkText("Sign up")).click();
        Thread.sleep(2000);
//        Type “test@example.com” text into Mobile Number or Email input
        driver.findElement(By.name("emailOrPhone")).sendKeys("test@example.com");
        Thread.sleep(2000);
//        Type “FirstName LastName” text into Full Name input
        driver.findElement(By.name("fullName")).sendKeys("Masrur Sattorov");
        Thread.sleep(2000);
//        Type “someUsername” text into Username input
        driver.findElement(By.name("username")).sendKeys("someUsername");
        Thread.sleep(2000);
//        Type “P@ssw0rd” text into Password input
        driver.findElement(By.name("password")).sendKeys("P@ssw0rd");
        Thread.sleep(2000);
//        Click on “Sign up” button
        driver.findElement (By.xpath("//button[.='Sign up']")).click();
        Thread.sleep(2000);
 //       To close browser
        driver.quit();

    }
}
