package com.saucedemo.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Indeed {
    public static void main (String[] args) {
       WebDriver driver = new ChromeDriver();
       //Visiting indeed website
       driver.get("https://www.indeed.com/");
       driver.findElement(By.xpath("//input")).click();
       driver.findElement(By.xpath("//input")).sendKeys("Qa");
       driver.findElement(By.xpath("//button")).click();
       driver.findElement(By.id("sja1")).click();
       WebElement applyButton = driver.findElement(By.xpath("//a[.='Apply Now']"));
       WebElement applyOnWebsite = driver.findElement(By.xpath("//a[.='Apply On Company Site']"));
       applyButton.click();
       if (applyButton!=applyOnWebsite){
           applyButton.click();
       }else {
           applyOnWebsite.click();
       }

driver.quit();




    }


}