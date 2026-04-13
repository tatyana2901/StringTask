package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class FileParser {

    private String filePath;

    public FileParser(String filePath) throws IOException {
        this.filePath = filePath;
    }

    public List<List<String>> getUniqueLines() throws IOException {


        try (GZIPInputStream gzipStream = new GZIPInputStream(Files.newInputStream(Paths.get(filePath)));
             BufferedReader reader = new BufferedReader(new InputStreamReader(gzipStream))) {

            String line;
            int k = 0;

            while ((line = reader.readLine()) != null) {

                System.out.println(line);
                System.out.println(k);
                k++;

            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


}
