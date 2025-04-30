package org.example.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SearchResultsFileWriter {

    private static Path resultDir;

    public static Path setValidFileName(String fileName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd--HH-mm-ss"));

        return Path.of(fileName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".txt");
    }

    //  how to set your own dir? getProperty(user.home)?
    public static Path getResultDir() throws IOException {
        if (resultDir == null) {
            resultDir = Path.of("results");
            if (!Files.exists(resultDir)) {
                Files.createDirectories(resultDir);
            }
        }
        return resultDir;
    }

    public static void createResultFile(String fileName, List<String> searchResults) {
        try {

            Path directory = getResultDir();
            Path resultFile = directory.resolve(setValidFileName(fileName));

            Files.createFile(resultFile);
            Files.write(resultFile, searchResults);
            System.out.println("Result file created");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("No results!");
        }
    }
}

/*
        choose path for file
        public static void chooseResultFileDirectory() {
        System.out.println("Input directory to save file: ");
        String writePath = new Scanner(System.in).nextLine();
    }


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
