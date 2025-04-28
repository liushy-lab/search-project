package org.example.utils;

import java.util.List;
import java.util.Scanner;

public class ConsolePrinter {

    public static String askRequest() {
        System.out.println("Input your query" + "\n");
        return new Scanner(System.in).nextLine();
    }

    public static void printResults(List<String> resultList) {
        if (resultList.isEmpty()) {
            System.out.println("No results found!");
        } else {
            System.out.println("Results in total: " + resultList.size());
            resultList.forEach(System.out::println);
        }
    }
}
