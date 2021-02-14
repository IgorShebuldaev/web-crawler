package org.webcrawler.data;

import java.util.*;
import java.util.stream.Collectors;

public class Results {
    private HashMap<String, Stats> results;
    private int depth = 8;
    private int pageLimit = 10000;

    public Results(List<Integer> parameters) {
        this.results = new HashMap<>();
        setNewParameters(parameters);
    }

    public int getDepth() {
        return depth;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void addResult(String url, ArrayList<Integer> stats) {
        results.put(url, new Stats(url, stats));
    }

    public boolean isPageProcessed(String link) {
        return results.get(link) != null;
    }

    public boolean isLimitExceeded() {
        return results.size() > pageLimit;
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

    private void setNewParameters(List<Integer> parameters) {
        if (parameters.get(0) != 0) depth = parameters.get(0);
        if (parameters.get(1) != 0) pageLimit = parameters.get(1);
    }
}
