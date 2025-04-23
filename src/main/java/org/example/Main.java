package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.utils.SearchResultsFileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Input your query");
        String searchQuery = new Scanner(System.in).nextLine();

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

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

            /*
            Path resultsFile = Path.of(searchQuery.replaceAll("[^a-zA-Z0-9]", "_") + ".txt");
            try {
                Files.createFile(resultsFile);
                Files.write(resultsFile, resultsList);
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
                System.out.println("No results!");
            }
            */

            driver.quit();
        }
    }
}