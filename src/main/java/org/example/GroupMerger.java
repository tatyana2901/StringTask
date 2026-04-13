package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GroupMerger {

    public static List<Set<String>> mergeWithUnionFind(List<Set<String>> inputSets) {
        if (inputSets == null) return Collections.emptyList();

        DSU dsu = new DSU();

        for (Set<String> set : inputSets) {
            if (set == null || set.isEmpty()) continue;

            Iterator<String> it = set.iterator();
            String first = it.next();
            dsu.add(first);

            while (it.hasNext()) {
                String next = it.next();
                dsu.add(next);
                dsu.union(first, next);
            }
        }

        Map<String, Set<String>> components = new HashMap<>();
        for (String s : new ArrayList<>(dsu.getAllElements())) {
            String root = dsu.find(s);
            components.computeIfAbsent(root, k -> new HashSet<>()).add(s);
        }
        return new ArrayList<>(components.values());
    }

}

class DSU {
    private final Map<String, String> parent = new HashMap<>();

    public void add(String s) {
        parent.putIfAbsent(s, s);
    }

    public String find(String s) {
        if (!parent.get(s).equals(s)) {
            parent.put(s, find(parent.get(s)));
        }
        return parent.get(s);
    }

    public void union(String a, String b) {
        String rootA = find(a);
        String rootB = find(b);
        if (!rootA.equals(rootB)) {
            parent.put(rootB, rootA);
        }
    }

    public Set<String> getAllElements() {
        return Collections.unmodifiableSet(parent.keySet());
    }
}
