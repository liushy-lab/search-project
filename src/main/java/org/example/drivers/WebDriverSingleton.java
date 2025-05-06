package org.example.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverSingleton {
    private static WebDriver driver;

    private WebDriverSingleton() {};

    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless=new");
            chromeOptions.addArguments("--disable-gpu"); // outdated
            chromeOptions.addArguments("--window-size=1920, 1080");

            driver = new ChromeDriver(chromeOptions);
        } return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
