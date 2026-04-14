package org.example;

import java.util.*;
import java.util.function.ToIntFunction;

public class LineMatchesFinder {

    private List<String[]> strings = new ArrayList<>();

    public LineMatchesFinder(List<String[]> strings) {
        this.strings = strings;
    }

    public List<Set<Integer>> findMatches() {

        if (strings == null || strings.isEmpty()) {
            System.out.println("нет данных.");
            return new ArrayList<>();
        }

        List<Set<Integer>> matchesSets = new ArrayList<>();
        Map<String, Set<Integer>> stringNumbers = new HashMap<>(); //мапа k - значение строки itemValue , v - номер строки j
        for (int i = 0; i < getLongestStringSize(); i++) {
            stringNumbers.clear();

            for (int j = 0; j < strings.size(); j++) {
                String[] line = strings.get(j);  // получили строку
                if (i < line.length) {                //проверка что i не выходит за пределы размера текущей строки + добавить условие непустого значения
                    String itemValue = line[i]; // получили элемент строки в текущем столбце i
                    if (itemValue.equals("\"\"")) {//если значение вида "" - пропускем его
                        continue;
                    }

                    Set<Integer> matches = stringNumbers.get(itemValue);
                    if (matches == null) {
                        matches = new HashSet<>();
                        stringNumbers.put(itemValue, matches);
                    }
                    matches.add(j);
                }

            }
            for (Set<Integer> matches : stringNumbers.values()) {// добавили множество всех совпадений (более 1 элемента в сете совпадений) значений по колонке
                if (matches.size() > 1) {
                    matchesSets.add(matches);
                }
            }

        }
        return matchesSets;
    }

    private long getLongestStringSize() {
        return strings.stream()
                .mapToInt(value -> value.length)
                .max()
                .orElse(0);
    }

}
