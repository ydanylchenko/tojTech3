package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TextInField {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://github.com/gogr/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifyTextInField() {
//        System.out.println(driver.findElement(By.tagName("h1")).findElement(By.xpath("//span[@itemprop=\"name\"]")).getText());
        String fullName = driver.findElement(By.xpath("//span[@itemprop=\"name\"]")).getText();
//        {
//            String fullName1;
//            By locator = By.xpath("//span[@itemprop=\"name\"]");
//            WebElement element = driver.findElement(locator);
//            fullName1 = element.getText();
//        }
        System.out.println(fullName);
        String userName;
        userName = driver.findElement(By.className("p-nickname")).getText();
        System.out.println(userName);
        driver.quit();
    }

    @Test
    public void verifyTextInInput() {
//        {
//        System.out.print("Execution of getText method: '");
//        System.out.print(driver.findElement(By.name("q")).getText());
//        System.out.println("'");
//        }
        System.out.println("Execution of getText method: '" + driver.findElement(By.name("q")).getText() + "'"); // no text
        System.out.println("Execution of getAttribute 'placeholder' method: '" + driver.findElement(By.name("q")).getAttribute("placeholder") + "'"); // the placeholder
        driver.findElement(By.name("q")).sendKeys("myCoolRepo");
        System.out.println("Execution of getText method: '" + driver.findElement(By.name("q")).getText() + "'"); // not text
        System.out.println("Execution of getAttribute 'value' method: '" + driver.findElement(By.name("q")).getAttribute("value") + "'"); // myCoolRepo
        driver.quit();
    }
}