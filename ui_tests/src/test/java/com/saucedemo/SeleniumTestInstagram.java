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
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[3]/div/label/input")).sendKeys("test@example.com");
        Thread.sleep(2000);
//        Type “FirstName LastName” text into Full Name input
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[4]/div/label/input")).sendKeys("Masrur Sattorov");
        Thread.sleep(2000);
//        Type “someUsername” text into Username input
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[5]/div/label/input")).sendKeys("someUsername");
        Thread.sleep(2000);
//        Type “P@ssw0rd” text into Password input
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[6]/div/label/input")).sendKeys("P@ssw0rd");
        Thread.sleep(2000);
//        Click on “Sign up” button
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[7]/div")).click();
        Thread.sleep(3000);
 //       To close browser
        driver.quit();

    }
}
