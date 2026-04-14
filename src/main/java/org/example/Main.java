package org.example;

import java.io.IOException;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        long startTime1 = System.currentTimeMillis();
        Runtime runtime1 = Runtime.getRuntime();
        long startMemory1 = runtime1.totalMemory() - runtime1.freeMemory();
        //    ("C:\\Users\\anisi\\Downloads\\lng-4.txt.gz");
        FileParser parser = new FileParser("C:\\Users\\tanisimova\\Downloads\\lng-4.txt.gz");
        List<String[]> uniqueStr = parser.getUniqueLines(); //!!!

        LineMatchesFinder finder = new LineMatchesFinder(uniqueStr); //!!!


        List<Set<Integer>> lineMatches = finder.findMatches(); //номера совпадающих строк в рамках одного столбца

        List<Set<Integer>> mergedGroups = GroupMerger.mergeWithUnionFind(lineMatches); //Номера строк, которые объединены в итоговые множества

        ResultMaker.writeGroups(mergedGroups, uniqueStr);

       long endTime6 = System.currentTimeMillis();
        long endMemory6 = runtime1.totalMemory() - runtime1.freeMemory();

        /*System.out.println("\n=== РЕЗУЛЬТАТЫ ВЫПОЛНЕНИЯ ===");
         System.out.printf(" Время: %.3f сек%n", (endTime1 - startTime1) / 1000.0);
        System.out.printf(" Память: %d MB%n", (endMemory1 - startMemory1) / (1024 * 1024));
        System.out.printf(" Всего использовано: %d MB%n", endMemory1 / (1024 * 1024));
*/

       /* System.out.println("\n=== РЕЗУЛЬТАТЫ ВЫПОЛНЕНИЯ 2 ===");
        System.out.printf(" Время: %.3f сек%n", (endTime2 - startTime2) / 1000.0);
        System.out.printf(" Память: %d MB%n", (endMemory2 - startMemory2) / (1024 * 1024));
        System.out.printf(" Всего использовано: %d MB%n", endMemory2 / (1024 * 1024));

        System.out.println("\n=== РЕЗУЛЬТАТЫ ВЫПОЛНЕНИЯ 3===");
        System.out.printf(" Время: %.3f сек%n", (endTime3 - startTime3) / 1000.0);
        System.out.printf(" Память: %d MB%n", (endMemory3 - startMemory3) / (1024 * 1024));
        System.out.printf(" Всего использовано: %d MB%n", endMemory3 / (1024 * 1024));

        System.out.println("\n=== РЕЗУЛЬТАТЫ ВЫПОЛНЕНИЯ 4===");
        System.out.printf(" Время: %.3f сек%n", (endTime4 - startTime4) / 1000.0);
        System.out.printf(" Память: %d MB%n", (endMemory4 - startMemory4) / (1024 * 1024));
        System.out.printf(" Всего использовано: %d MB%n", endMemory4 / (1024 * 1024));


        System.out.println("\n=== РЕЗУЛЬТАТЫ ВЫПОЛНЕНИЯ 5===");
        System.out.printf(" Время: %.3f сек%n", (endTime5 - startTime5) / 1000.0);
        System.out.printf(" Память: %d MB%n", (endMemory5 - startMemory5) / (1024 * 1024));
        System.out.printf(" Всего использовано: %d MB%n", endMemory5 / (1024 * 1024));*/

        System.out.println("\n=== РЕЗУЛЬТАТЫ ВЫПОЛНЕНИЯ ИТОГО===");
        System.out.printf(" Время: %.3f сек%n", (endTime6 - startTime1) / 1000.0);
        System.out.printf(" Память: %d MB%n", (endMemory6 - startMemory1) / (1024 * 1024));
        System.out.printf(" Всего использовано: %d MB%n", endMemory6 / (1024 * 1024));
    }


}