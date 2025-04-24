package org.example.pages;

import org.example.drivers.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DuckDuckGoPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//textarea[@name='q']")
    private WebDriver searchBar;

    @FindBy()
    private WebDriver searchResult;

    @FindBy(xpath = "//button[@id='more-results']")
    private WebDriver nextPage;

    public DuckDuckGoPage(WebDriver driver) {
        this.driver = WebDriverSingleton.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
}
