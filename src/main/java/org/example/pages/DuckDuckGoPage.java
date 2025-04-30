package org.example.pages;

import org.example.drivers.WebDriverSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DuckDuckGoPage {
    WebDriver driver;
    WebDriverWait wait;

    private static final String HOME_PAGE = "https://duckduckgo.com/";

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchBar;

    @FindBy(xpath = "//button[@id='more-results']")
    private WebElement nextPageButton;

    @FindBy(xpath = "//article[contains(@id, 'r1-')]")
    private List<WebElement> searchResults;

    public DuckDuckGoPage() {
        this.driver = WebDriverSingleton.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void doSearch(String query) {
        driver.get(HOME_PAGE);
        searchBar.click();
        searchBar.clear();
        searchBar.sendKeys(query + Keys.ENTER);
    }

    private List<WebElement> getSearchResults() {
        return searchResults;
    }

    private void scrollAndClickNextPage() {
        Actions actions = new Actions(driver);

        actions.moveToElement(nextPageButton).perform();
        actions.click(nextPageButton).perform();
    }

    public List<WebElement> getAllResults() {

        List<WebElement> list = new ArrayList<>();
        list.addAll(getSearchResults());

        while (true) {
            try {
                scrollAndClickNextPage();
                list.addAll(getSearchResults());
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                System.out.println("That was the last page!");
                break;
            }
        }
        return list;
    }
}
