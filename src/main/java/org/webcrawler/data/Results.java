package org.webcrawler.data;

import java.util.*;
import java.util.stream.Collectors;

public class Results {
    private HashMap<String, Stats> results;

    public Results() {
        results = new HashMap<>();
    }

    public boolean isPageProcessed(String link) {
        return results.get(link) != null;
    }

    public int size() {
        return results.size();
    }

    public ArrayList<Stats> values() {
        return new ArrayList<>(results.values());
    }

    public ArrayList<Stats> getSortedStats() {
        return results.values().stream().sorted((s1, s2) -> {
            Integer t1 = s1.total();
            Integer t2 = s2.total();

            return t2.compareTo(t1);
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    public void addResult(String url, ArrayList<Integer> stats) {
        results.put(url, new Stats(url, stats));
    }
}
