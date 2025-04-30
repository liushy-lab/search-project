package org.example.pages;

import org.example.drivers.WebDriverSingleton;
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

public class YandexPage {
    WebDriver driver;
    WebDriverWait wait;

    private static final String HOME_PAGE = "https://yandex.com/";

    @FindBy(xpath = "//input[@name='text']")
    private WebElement searchBar;

    @FindBy(xpath = "//li[contains(@class, 'serp-item')]")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//a[contains(@class, 'Pager-Item_type_next')]")
    private WebElement nextPageButton;

    public YandexPage() {
        this.driver = WebDriverSingleton.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
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
