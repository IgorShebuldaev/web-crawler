package org.webcrawler;

import java.util.*;
import java.util.stream.Collectors;

public class Results {
    private HashMap<String, Stats> results;
    private final int RESULTS_LIMIT = 100;

    public Results() {
        this.results = new HashMap<>();
    }

    public void addResult(String url, ArrayList<Integer> stats) {
        results.put(url, new Stats(url, stats));
    }

    public boolean isPageProcessed(String link) {
        return results.get(link) != null;
    }

    public boolean isLimitExceeded() {
        return results.size() > RESULTS_LIMIT;
    }

    public ArrayList<Stats> getSortedStats() {
        return results.values().stream().sorted((s1, s2) -> {
            Integer t1 = s1.total();
            Integer t2 = s2.total();

            return t2.compareTo(t1);
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Stats> values() {
        return new ArrayList<>(results.values());
    }
}
