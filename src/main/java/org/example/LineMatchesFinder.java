package org.example;

import java.util.*;

public class LineMatchesFinder {

    private List<List<String>> strings = new ArrayList<>();

    public LineMatchesFinder(List<List<String>> strings) {
        this.strings = strings;
    }

    public List<Set<String>> findMatches() {

        if (strings == null || strings.isEmpty()) {
            System.out.println("нет данных.");
            return new ArrayList<>();
        }

        List<Set<String>> matchesSets = new ArrayList<>();

        for (int i = 0; i < getLongestStringSize(); i++) {
            Map<String, Set<String>> stringNumbers = new HashMap<>(); //мапа k - значение строки itemValue , v - номер строки j

            for (int j = 0; j < strings.size(); j++) {
                List<String> line = strings.get(j);  // получили строку
                if (i < line.size()) {                //проверка что i не выходит за пределы размера текущей строки + добавить условие непустого значения
                    String itemValue = line.get(i); // получили элемент строки в текущем столбце i
                    if (itemValue.isEmpty()) {//если значение вида " " - пропускем его
                        continue;
                    }
                    stringNumbers.merge(itemValue, new HashSet<>(Set.of(j + "")), (i1, i2) -> { //дополнение списка мапы значения новым элментом
                        i1.addAll(i2);
                        return i1;
                    });
                }

            }
            for (Set<String> matches : stringNumbers.values()) {// добавили множество всех совпадений (более 1 элемента в сете совпадений) значений по колонке
                if (matches.size() > 1) {
                    matchesSets.add(matches);
                }
            }

        }
      //  System.out.println("values" + matchesSets);

        return matchesSets;
    }

    private long getLongestStringSize() {
        return strings.stream()
                .mapToInt(List::size)
                .max()
                .orElse(0);
    }

}
