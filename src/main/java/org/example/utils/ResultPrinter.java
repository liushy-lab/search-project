package org.example.utils;

import java.util.List;

public class ResultPrinter {
    public static void printResults(List<String> resultsList, String searchQuery) {
        if (resultsList.isEmpty()) {
            System.out.println("No results found!");
        } else {
            System.out.println("Results in total: " + resultsList.size());
            resultsList.forEach(System.out::println);
        }
    }
}
