package org.example.pages;

import org.example.drivers.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GooglePage {
    WebDriver driver;
    WebDriverWait wait;

    String homePage = "https://google.com";

    @FindBy(xpath = "//textarea[@name='q']")
    private WebDriver searchBar;

    @FindBy(xpath = "//div[@class='MjjYud']")
    private WebDriver searchResult;

    @FindBy(xpath = "//td[@class='NKTSme']")
    private WebDriver nextPage;

    public GooglePage(WebDriver driver) {
    this.driver = WebDriverSingleton.getDriver();
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

}
