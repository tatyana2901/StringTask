package org.example;

import java.io.BufferedReader;
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

        try (GZIPInputStream gzipStream = new GZIPInputStream(Files.newInputStream(Paths.get(filePath)));
             BufferedReader reader = new BufferedReader(new InputStreamReader(gzipStream))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (!uniqueStrings.add(line)) {
                    continue;
                }
                dataArrays.add(line.split(";"));
            }

        } catch (Exception e) {
            System.err.println("Ошибка чтения: " + e.getMessage());
        }

        return dataArrays;
    }


}
