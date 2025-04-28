package org.example.utils;

import org.example.pages.DuckDuckGoPage;
import org.example.pages.GooglePage;
import org.example.pages.YandexPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Scanner;

public class ListFormatter {



    public String chooseSeparator() {
        System.out.println("Which separator would you prefer:\n" +
                "star\n" +
                "plato\n" +
                "wave\n");

        String separator = new Scanner(System.in).nextLine().toLowerCase();

        switch (separator) {
            case "1", "star", "asterisk":
                return "\n**********\n";
            case "2", "plain", "underscore":
                return "\n__________\n";
            case "3", "wave", "tilda":
                return "\n~~~~~~~~~~\n";
            default:
                return "\n__________\n";
        }
    }
}
