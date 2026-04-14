package org.example;

import java.util.List;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("Не указан путь к файлу");
            System.exit(1);
        }

        String filePath = args[0];

        long startTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        long startMemory = runtime.totalMemory() - runtime.freeMemory();

        FileParser parser = new FileParser(filePath);
        List<String[]> inputData = parser.getInputStrings();

        LineMatchesFinder finder = new LineMatchesFinder(inputData);

        List<Set<Integer>> lineMatches = finder.findMatches();

        List<Set<Integer>> mergedGroups = GroupMerger.mergeWithUnionFind(lineMatches);

        ResultMaker.writeGroups(mergedGroups, inputData);

        long endTime = System.currentTimeMillis();
        long endMemory = runtime.totalMemory() - runtime.freeMemory();


        System.out.println("РЕЗУЛЬТАТЫ ВЫПОЛНЕНИЯ:");
        System.out.printf(" Время: %.3f сек%n", (endTime - startTime) / 1000.0);
        System.out.printf(" Память: %d MB%n", (endMemory - startMemory) / (1024 * 1024));
        System.out.printf(" Всего использовано: %d MB%n", endMemory / (1024 * 1024));
    }


}