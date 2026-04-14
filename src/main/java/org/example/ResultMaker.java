package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Set;

public class ResultMaker {

    static final String OUTPUTFILE = "groups_output.txt";

    public static void writeGroups(List<Set<Integer>> mergedGroups, List<String[]> inputData) {
        Path outputPath = Paths.get(OUTPUTFILE);

        try (BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

            writer.write("Всего получено " + mergedGroups.size() + " групп.");
            writer.newLine();

            int k = 0;
            for (Set<Integer> mergedGroup : mergedGroups) {
                writer.write("Группа " + ++k);
                writer.newLine();
                for (Integer i : mergedGroup) {
                    writer.write(String.join(";", inputData.get(i)));
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.err.println("Ошибка записи: " + e.getMessage());
        }
    }

}
