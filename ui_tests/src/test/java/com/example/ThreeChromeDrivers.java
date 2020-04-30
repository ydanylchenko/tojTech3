package com.example;

import org.openqa.selenium.chrome.ChromeDriver;

public class ThreeChromeDrivers {
    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver_1 = new ChromeDriver();
        ChromeDriver driver_2 = new ChromeDriver();
        ChromeDriver driver_3 = new ChromeDriver();
        driver_1.get("https://www.google.com/");
        driver_2.get("https://github.com/gogr/tojTech2/blob/master/src/test/java/day10/variablesVisibility/VariablesVisibilitySimpleBlocks.java");
        driver_3.get("http://www.asciitable.com/");
        System.out.println("driver_1 is at: " + driver_1.getCurrentUrl());
        System.out.println("driver_2 is at: " + driver_2.getCurrentUrl());
        System.out.println("driver_3 is at: " + driver_3.getCurrentUrl());
        Thread.sleep(5000);
        driver_1.quit();
        driver_2.quit();
        driver_3.quit();
    }
}
