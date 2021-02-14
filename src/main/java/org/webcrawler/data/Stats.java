package org.webcrawler.data;

import java.util.ArrayList;

public class Stats {
    public String url;
    public ArrayList<Integer> statistics;

    public Stats(String url, ArrayList<Integer> statistics) {
        this.url = url;
        this.statistics = statistics;
    }

    public int total() {
        return statistics.stream().mapToInt(Integer::intValue).sum();
    }
}
