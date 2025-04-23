package org.example.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class SearchResultsFileWriter {

    private static Path resultDir;

//    choose path for file
//    public static void chooseResultFileDirectory() {
//        System.out.println("Input directory to save file: ");
//        String writePath = new Scanner(System.in).nextLine();
//    }

    public static Path setValidFileName(String fileName) {
        return Path.of(fileName.replaceAll("[^a-zA-Z0-9]", "_") + ".txt");
    }

    public static Path getResultDir() throws IOException {
        if (resultDir == null) {
            resultDir = Path.of("results");
            if (!Files.exists(resultDir)) {
                Files.createDirectories(resultDir);
            }
        }
        return resultDir;
    }

    /*
    public static Path createDirectory() throws IOException {
        if (Files.exists(resultDir)) {
            if (Files.isDirectory(resultDir)) {
                System.out.println("Directory already exists!");
            } else {
                Files.createDirectory((resultDir));
            }
        } return resultDir;
    }
    */


    public static void createResultFile(String fileName, List<String> searchResults) {
        try {
        Path directory = getResultDir();
        Path directoryWithFile = directory.resolve(setValidFileName(fileName));

            Files.createFile(directoryWithFile);
            Files.write(directoryWithFile, searchResults);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("No results!");
        }
    }
}

