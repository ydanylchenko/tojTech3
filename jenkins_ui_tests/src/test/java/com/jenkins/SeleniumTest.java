package com.jenkins;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World");
//        To Start browser
        WebDriver driver;
        driver = new ChromeDriver();
        Thread.sleep(1500);
//        To open a URL
        driver.get("https://www.selenium.dev/");
        Thread.sleep(1500);
//        To located title element on page
        WebElement title = driver.findElement(By.xpath("//h1"));
//        WebElement title = driver.findElement(By.tagName("h1"));
        Thread.sleep(1500);
//        To get text from the located title element
        System.out.println(title.getText());
        Thread.sleep(1500);
//        To click on element
//        driver.findElement(By.xpath("//a[.='Downloads']")).click();
        driver.findElement(By.linkText("Downloads")).click();
        Thread.sleep(1500);
//        To type something in input
        driver.findElement(By.name("search")).sendKeys("I typed This!");
        Thread.sleep(1500);
//        To close browser
        driver.quit();
    }
}
