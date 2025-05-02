package org.example.utils;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Scanner;

public class ResultListFormatter {

    static Scanner scanner = new Scanner(System.in);

    public static List<String> formatResultList(String query, List<WebElement> resultList) {
        String separator = chooseSeparator();

        // make description smaller, mb filter by smth more relevant?
        return resultList.stream()
                .map(WebElement::getText)
                .filter(request -> request.contains(query))
                .map(address -> address.replace(" › ", "/"))
                .map(text -> text + separator)
                .toList();
    }

    public static String chooseSeparator() {
        System.out.println("Which one would you prefer?\n" +
                "* star\n" +
                "_ plain\n" +
                "~ wave");

        String separator = scanner.nextLine().toLowerCase();

        switch (separator) {
            case "1", "star", "asterisk":
                return "*";
            case "2", "plain", "underscore":
                return "_";
            case "3", "wave", "tilda":
                return "~";
            default:
                return "_";
        }
    }

    public static int chooseAmountOfSeparators() {
        System.out.println("How many symbols you want?");
        int amount = Integer.parseInt(scanner.nextLine());

        while (true) {
            if (amount <= 50) {
                return amount;
            }
            System.out.println("Your number should be between 1 and 50! Try again:");
        }
    }

    public static String printOutSeparator() {

        String separator = chooseSeparator();
        int amount = chooseAmountOfSeparators();

        return separator.repeat(amount);
    }
}

