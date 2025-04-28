package org.example;

import org.example.drivers.WebDriverSingleton;
import org.example.pages.DuckDuckGoPage;
import org.example.pages.GooglePage;
import org.example.pages.YandexPage;
import org.example.utils.ConsolePrinter;
import org.example.utils.ResultListFormatter;
import org.example.utils.SearchResultsFileWriter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static DuckDuckGoPage duckDuckGoPage;
    private static GooglePage googlePage;
    private static YandexPage yandexPage;

    public static void main(String[] args) {

        driver = WebDriverSingleton.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        duckDuckGoPage = new DuckDuckGoPage(driver);
        googlePage = new GooglePage(driver);
        yandexPage = new YandexPage(driver);

        Scanner scanner = new Scanner(System.in);

        String searchQuery = ConsolePrinter.askRequest(scanner);
        ResultListFormatter.chooseSeparator();

        duckDuckGoPage.doSearch(searchQuery);
        List<WebElement> resultList = duckDuckGoPage.getSearchResults();

        List<String> formattedList = ResultListFormatter.formatResultList(searchQuery, resultList);

        SearchResultsFileWriter.createResultFile(searchQuery, formattedList);

        ConsolePrinter.printResults(formattedList);

        WebDriverSingleton.quitDriver();
    }
}

        /*
        driver.get("https://duckduckgo.com/");

        WebElement searchBar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='q']")));
        searchBar.click();
        searchBar.clear();
        searchBar.sendKeys(searchQuery + Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//article[contains(@id, 'r1-')]")));
        List<WebElement> searchResults = driver.findElements(By.xpath("//article[contains(@id, 'r1-')]"));
//        ArrayList<String> resultsList = new ArrayList<>();
//        for (WebElement result : searchResults) {
//            resultList.add(result.getText() + "\n" + "_______________________");
//        }

        List<String> resultsList = searchResults.stream()
                .map(WebElement::getText)
                .filter(query -> query.contains(searchQuery))
                .map(address -> address.replace(" › ", "/"))
                .map(text -> text + "\n__________\n")
                .toList();

//        WebElement nextPage = driver.findElement(By.xpath("//button[@id='more-results']"));
//        nextPage.click();

        if (resultsList.isEmpty()) {
            System.out.println("No results found!");
        } else {
            System.out.println("Results in total: " + resultsList.size());
            resultsList.forEach(System.out::println);

            SearchResultsFileWriter.createResultFile(searchQuery, resultsList);
            SearchResultsFileWriter.getResultDir();

            driver.quit();
        }
    }
         */
