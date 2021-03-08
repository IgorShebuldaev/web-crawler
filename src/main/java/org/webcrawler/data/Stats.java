package org.webcrawler.data;

import java.util.ArrayList;

public class Stats {
    private String url;
    private ArrayList<Integer> statistics;

    public String getUrl() {
        return url;
    }

    public ArrayList<Integer> getStatistics() {
        return statistics;
    }

    public Stats(String url, ArrayList<Integer> statistics) {
        this.url = url;
        this.statistics = statistics;
    }

    public int total() {
        return statistics.stream().mapToInt(Integer::intValue).sum();
    }
}
