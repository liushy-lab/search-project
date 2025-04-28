package org.example.pages;

import org.example.drivers.WebDriverSingleton;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GooglePage {
    WebDriver driver;
    WebDriverWait wait;

    private static final String HOME_PAGE = "https://google.com/";

    @FindBy(xpath = "//textarea[@name='q']")
    private WebElement searchBar;

    @FindBy(xpath = "//div[@class='MjjYud']")
    private WebElement searchResult;

    @FindBy(xpath = "//td[@class='NKTSme']")
    private WebElement nextPage;

    public GooglePage(WebDriver driver) {
    this.driver = WebDriverSingleton.getDriver();
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void doSearch(String searchQuery) {
        driver.get(HOME_PAGE);
        searchBar.click();
        searchBar.clear();
        searchBar.sendKeys(searchQuery + Keys.ENTER);
    }
}
