package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Input your query");
        String searchQuery = new Scanner(System.in).nextLine();

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://duckduckgo.com/");

        WebElement searchBar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='q']")));
        searchBar.click();
        searchBar.clear();
        searchBar.sendKeys(searchQuery + Keys.ENTER);

        List<String> resultList = new ArrayList<>();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//article[contains(@id, 'r1-'")));
        List<WebElement> searchResults = driver.findElements(By.xpath("//article"));
        for (WebElement result : searchResults) {
            resultList.add(result.getText() + "\n" + "_______________________");
        }


//        WebElement nextPage = driver.findElement(By.xpath("//button[@id='more-results']"));
//        nextPage.click();


        if (resultList.isEmpty()) {
            System.out.println("result list is empty");
        } else {
            Path resultsFile = Path.of(searchQuery.replaceAll("[^a-zA-Z0-9]", "_") + ".txt");
            try {
                Files.createFile(resultsFile);
                Files.write(resultsFile, resultList);
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
                System.out.println("No results!");
            }

            driver.quit();
        }
    }
}