package org.example.pages;

import org.example.drivers.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class YandexPage {
    WebDriver driver;
    WebDriverWait wait;

    private static final String HOME_PAGE = "https://yandex.com/";

    @FindBy(xpath = "//input[@name='text']")
    private WebElement searchBar;

    @FindBy(xpath = "//li[contains(@class, 'serp-item')]")
    private WebElement searchResult;

    @FindBy(xpath = "//a[contains(@class, 'Pager-Item_type_next')]")
    private WebElement nextPage;

    public YandexPage(WebDriver driver) {
        this.driver = WebDriverSingleton.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
}

//List<WebElement> searchResults = driver.findElements(By.xpath("//article"));
//List<String> resultsList = searchResults.stream()
//        .map(WebElement::getText)
//        .toList();