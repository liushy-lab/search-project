package org.example.utils;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Scanner;

public class ResultListFormatter {

//    public static List<String> getFormattedResultList(String searchQuery, List<WebElement> resultList) {
//        return getFormattedResultList()
//    }

    public static List<String> formatResultList(String query, List<WebElement> resultList) {
        String separator = chooseSeparator();

        return resultList.stream()
                .map(WebElement::getText)
                .filter(request -> request.contains(query))
                .map(address -> address.replace(" › ", "/"))
                .map(text -> text + separator)
                .toList();
    }

    public static String chooseSeparator() {
        System.out.println("What would you prefer seeing:\n" +
                "star\n" +
                "plain\n" +
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
