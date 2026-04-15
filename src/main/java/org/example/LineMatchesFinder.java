package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LineMatchesFinder {

    private List<String[]> strings;

    public LineMatchesFinder(List<String[]> strings) {
        this.strings = strings;
    }

    public List<Set<Integer>> findMatches() {

        if (strings == null || strings.isEmpty()) {
            System.out.println("нет данных.");
            return new ArrayList<>();
        }

        List<Set<Integer>> matchesSets = new ArrayList<>();

        Map<String, Set<Integer>> stringNumbers = new HashMap<>();

        for (int i = 0; i < getLongestStringSize(); i++) {
            stringNumbers.clear();

            for (int j = 0; j < strings.size(); j++) {
                String[] line = strings.get(j);
                if (i < line.length) {
                    String itemValue = line[i];

                    if (isEmptyValue(itemValue)) {
                        continue;
                    }
                    Set<Integer> matches = stringNumbers.computeIfAbsent(itemValue, k -> new HashSet<>());
                    matches.add(j);
                }
            }
            for (Set<Integer> matches : stringNumbers.values()) {
                if (matches.size() > 1) {
                    matchesSets.add(matches);
                }
            }
        }
        return matchesSets;
    }

    private boolean isEmptyValue(String value) {
        if (value == null) return true;

        value = value.trim();

        return value.isEmpty() ||
                value.equals("\"\"");

    }

    private long getLongestStringSize() {
        return strings.stream()
                .mapToInt(value -> value.length)
                .max()
                .orElse(0);
    }

}
