package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPInputStream;

public class FileParser {

    private String filePath;

    public FileParser(String filePath) {
        this.filePath = filePath;
    }

    public List<String[]> getInputStrings() {

        Set<String> uniqueStrings = new HashSet<>();
        List<String[]> dataArrays = new ArrayList<>();

        try (InputStream is = Files.newInputStream(Paths.get(filePath));
             InputStream decompressed = filePath.endsWith(".gz") ? new GZIPInputStream(is) : is;
             BufferedReader reader = new BufferedReader(new InputStreamReader(decompressed))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (!uniqueStrings.add(line)) {
                    continue;
                }
                dataArrays.add(line.split(";", -1));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка чтения: " + e.getMessage());
        }

        return dataArrays;
    }

}
