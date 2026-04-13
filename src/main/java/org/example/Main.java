package org.example;

import java.io.IOException;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        long startMemory = runtime.totalMemory() - runtime.freeMemory();

        FileParser parser = new FileParser("C:\\Users\\anisi\\Downloads\\lng-4.txt.gz");
        List<List<String>> uniqueStr = parser.getUniqueLines();


        LineMatchesFinder finder = new LineMatchesFinder(uniqueStr);
        List<Set<String>> lineMatches = finder.findMatches();
        List<Set<String>> mergedGroups = GroupMerger.mergeWithUnionFind(lineMatches);
      //  System.out.println(mergedGroups + "" + mergedGroups.size() + " ");

        long endTime = System.currentTimeMillis();
        long endMemory = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("\n=== РЕЗУЛЬТАТЫ ВЫПОЛНЕНИЯ ===");
        System.out.printf("⏱️  Время: %.3f сек%n", (endTime - startTime) / 1000.0);
        System.out.printf("💾 Память: %d MB%n", (endMemory - startMemory) / (1024 * 1024));
        System.out.printf("📦 Всего использовано: %d MB%n", endMemory / (1024 * 1024));
    }


}