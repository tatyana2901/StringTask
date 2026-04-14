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

    public List<String[]> getUniqueLines() throws IOException {

      //  Set<String> uniqueStrings = new HashSet<>();
       // List<String[]> uniqueLines = new ArrayList<>();

        Map<String, String[]> uniqueData = new LinkedHashMap<>();



        try (GZIPInputStream gzipStream = new GZIPInputStream(Files.newInputStream(Paths.get(filePath)));
             BufferedReader reader = new BufferedReader(new InputStreamReader(gzipStream))) {

            String line;



            while ((line = reader.readLine()) != null) {
                if (uniqueData.containsKey(line)) {
                    continue;
                }

                String[] array = line.split(";");

                uniqueData.put(line,array); //добавить проверку парсинга например регулярное выражение


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
       /* System.out.println(k + "всего выкинули дубликатов");
        System.out.println(uniqueLines.size() + " уникальных групп");
        System.out.println(uniqueStrings.size() + " уникальных строк");*/
       /* for (int i = 1; i <= uniqueLines.size(); i++) {
            System.out.println("Группа № " + i + " " + uniqueLines.get(i - 1));
        }*/
        return new ArrayList<>(uniqueData.values());
    }


}
