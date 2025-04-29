package org.example.utils;

import java.util.List;
import java.util.Scanner;

public class ConsolePrinter {

    public static String askRequest(Scanner scanner) {
        System.out.println("Input your query" + "\n");
        return scanner.nextLine();
    }

    public static void printResults(List<String> resultList) {
        if (resultList.isEmpty()) {
            System.out.println("No results found!");
        } else {
            System.out.print("Results in total: " + resultList.size());
            resultList.forEach(System.out::println);
        }
    }
}
