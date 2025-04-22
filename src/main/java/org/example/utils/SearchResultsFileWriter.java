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


    public static Path setValidFileName(String fileName) {
        return Path.of(fileName.replaceAll("[^a-zA-Z0-9]", "_") + ".txt");
    }

    public static Path createDirectory(Path path) throws IOException {
        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                System.out.println("Directory already exists!");
            } else {
                System.out.println("The path exists but it's not a directory!");
            }
        }
        return Files.createDirectory((path));
    }

    public static void createResultFile(String fileName, List<String> searchResults) {
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

