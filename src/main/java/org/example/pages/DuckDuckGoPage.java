package org.example.pages;

import org.example.drivers.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DuckDuckGoPage {
    WebDriver driver;
    WebDriverWait wait;

    private static final String HOME_PAGE = "https://duckduckgo.com/";

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchBar;

    @FindBy(xpath = "//button[@id='more-results']")
    private WebElement nextPage;

    @FindBy(xpath = "//article[contains(@id, 'r1-')]")
    private List<WebElement> searchResults;

    public DuckDuckGoPage(WebDriver driver) {
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

    public List<WebElement> getSearchResults() {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath("//article[contains(@id, 'r1-')]"))));
            return searchResults;
    }

    public void goToNextPage() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[@id='more-results']")));
        nextPage.click();
    }
}
