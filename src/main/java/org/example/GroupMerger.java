package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GroupMerger {

    public static List<Set<Integer>> mergeWithUnionFind(List<Set<Integer>> inputSets) {

        if (inputSets == null) return Collections.emptyList();

        DSU dsu = new DSU();

        for (Set<Integer> set : inputSets) {
            if (set == null || set.isEmpty()) continue;

            Iterator<Integer> it = set.iterator();
            Integer first = it.next();
            dsu.add(first);

            while (it.hasNext()) {
                Integer next = it.next();
                dsu.add(next);
                dsu.union(first, next);
            }
        }

        Map<Integer, Set<Integer>> components = new HashMap<>();
        for (Integer i : new ArrayList<>(dsu.getAllElements())) {
            Integer root = dsu.find(i);
            components.computeIfAbsent(root, k -> new HashSet<>()).add(i);
        }

        List<Set<Integer>> resultList = new ArrayList<>(components.values());
        resultList.sort(Comparator.<Set<Integer>>comparingInt(Set::size).reversed());

        return resultList;
    }

}

class DSU {
    private final Map<Integer, Integer> parent = new HashMap<>();

    public void add(Integer i) {
        parent.putIfAbsent(i, i);
    }

    public Integer find(Integer i) {
        if (!parent.get(i).equals(i)) {
            parent.put(i, find(parent.get(i)));
        }
        return parent.get(i);
    }

    public void union(Integer a, Integer b) {
        Integer rootA = find(a);
        Integer rootB = find(b);
        if (!rootA.equals(rootB)) {
            parent.put(rootB, rootA);
        }
    }

    public Set<Integer> getAllElements() {
        return Collections.unmodifiableSet(parent.keySet());
    }
}
