package org.example.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class SearchResultsFileWriter {


//    choose path for file
//    public static void chooseResultFileDirectory() {
//        System.out.println("Input directory to save file: ");
//        String writePath = new Scanner(System.in).nextLine();
//    }

//    make a correct name for file
    public static Path setValidFileName(String fileName) {
        return Path.of(fileName.replaceAll("[^a-zA-Z0-9]", "_") + ".txt");
    }

//    create a file
//    public Path createFile(String fileName) {
//        if ()
//    }

//    save file
    public static Path createFile(String fileName, List<String> searchResults) {
        Path results = setValidFileName(fileName);

        try {
            Files.createFile(results);
            Files.write(results, searchResults);
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            System.out.println("No results!");
        }
    }

}
