package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LocatorsSandBox {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void googleTest() {
        driver.get("https://www.google.com/");
        System.out.println("locating by:\n xpath(\"//input[@name='q'])\"" + "\n"
                + driver.findElement(By.xpath("//input[@name='q']")).getAttribute("title"));
        System.out.println("locating by:\n xpath(\"//input[@title='Search'])\"" + "\n"
                + driver.findElement(By.xpath("//input[@title='Search']")).getAttribute("title"));
        System.out.println("locating by:\n xpath(\"//input[@aria-label='Search'])\"" + "\n"
                + driver.findElement(By.xpath("//input[@aria-label='Search']")).getAttribute("title"));
        System.out.println("locating by:\n xpath(\"//*[@name='q'])\"" + "\n"
                + driver.findElement(By.xpath("//*[@name='q']")).getAttribute("title"));
        System.out.println("locating by:\n xpath(\"//*[@title='Search'])\"" + "\n"
                + driver.findElement(By.xpath("//*[@title='Search']")).getAttribute("title"));
        System.out.println("locating by:\n xpath(\"//*[@aria-label='Search'])\"" + "\n"
                + driver.findElement(By.xpath("//*[@aria-label='Search']")).getAttribute("title"));
        System.out.println("locating by:\n name(\"q)\"" + "\n"
                + driver.findElement(By.name("q")).getAttribute("title"));
    }

    @Test
    public void amazonCareersTest() {
        driver.get("https://www.amazon.jobs/en/");
        System.out.println("locating by:\n xpath(\"By.xpath(\"//button[@id='search-button']\")\"" + "\n"
                + driver.findElement(By.xpath("//button[@id='search-button']")).getAttribute("role"));
        System.out.println("locating by:\n xpath(\"By.xpath(\"//button[@class='btn location-search form-control']\")\"" + "\n"
                + driver.findElement(By.xpath("//button[@class='btn location-search form-control']")).getAttribute("role"));
        System.out.println("locating by:\n xpath(\"By.xpath(\"//button[@aria-labelledby='search-button-label']\")\"" + "\n"
                + driver.findElement(By.xpath("//button[@aria-labelledby='search-button-label']")).getAttribute("role"));
        System.out.println("locating by:\n xpath(\"By.xpath(\"//button[@id='search-button']\")\"" + "\n"
                + driver.findElement(By.xpath("//button[@id='search-button']")).getAttribute("role"));
        System.out.println("locating by:\n xpath(\"By.xpath(\"//button[@class='btn location-search form-control']\")\"" + "\n"
                + driver.findElement(By.xpath("//button[@class='btn location-search form-control']")).getAttribute("role"));
        System.out.println("locating by:\n xpath(\"By.xpath(\"//button[@aria-labelledby='search-button-label']\")\"" + "\n"
                + driver.findElement(By.xpath("//button[@aria-labelledby='search-button-label']")).getAttribute("role"));
    }
}







