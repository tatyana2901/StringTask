package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.GZIPInputStream;

public class FileParser {

    private String filePath;

    public FileParser(String filePath) throws IOException {
        this.filePath = filePath;
    }

    public List<List<String>> getUniqueLines() throws IOException {

        Set<String> uniqueStrings = new HashSet<>();
        List<List<String>> uniqueLines = new ArrayList<>();

        try (GZIPInputStream gzipStream = new GZIPInputStream(Files.newInputStream(Paths.get(filePath)));
             BufferedReader reader = new BufferedReader(new InputStreamReader(gzipStream))) {

            String line;
            int k = 1;


            while ((line = reader.readLine()) != null) {
                if (!uniqueStrings.add(line)) {
                    continue;
                }

                String[] array = line.split(";");

                uniqueLines.add(List.of(array)); //добавить проверку парсинга например регулярное выражение


               /* boolean b = ;
               if (!b){
                   System.out.println(" ДУБЛИРУЮЩАЯ СТРОКА " + line +"  "   + "номер " + k );
                   k++;
               }*/
                // System.out.println(line);
                // System.out.println(k);
                //k++;


            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }
      //  System.out.println(uniqueLines);
        return uniqueLines;
    }


}
