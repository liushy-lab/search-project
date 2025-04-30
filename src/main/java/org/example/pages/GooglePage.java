package org.example.pages;

import org.example.drivers.WebDriverSingleton;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GooglePage {
    WebDriver driver;
    WebDriverWait wait;

    private static final String HOME_PAGE = "https://google.com/";

    @FindBy(xpath = "//textarea[@name='q']")
    private WebElement searchBar;

    @FindBy(xpath = "//div[@class='MjjYud']")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//td[@class='NKTSme']")
    private WebElement nextPageButton;

    public GooglePage() {
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

    private List<WebElement> getSearchResults() {
        return searchResults;
    }

    private void scrollAndClickNextPage() {
        Actions actions = new Actions(driver);

        actions.moveToElement(nextPageButton).perform();
        actions.click(nextPageButton).perform();
    }

    //  may fail to scroll to the last page
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
