package org.webcrawler.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class TestResults {
    private Results results;
    private String link = "somelink";
    private ArrayList<Integer> stats = new ArrayList<>(Arrays.asList(0, 1, 2));

    @BeforeEach
    public void initialize() {
        results = new Results();
    }

    @Test
    public void testSize() {
        Assertions.assertEquals(0, results.size());
    }

    @Test
    public void testValues() {
        results.addResult(link, stats);
        ArrayList<Stats> list = new ArrayList<>(Collections.singletonList(new Stats(link, stats)));
        Assertions.assertEquals(list.size(), results.values().size());
        Assertions.assertEquals(list.get(0).getUrl(), results.values().get(0).getUrl());
        Assertions.assertEquals(list.get(0).getStatistics(), results.values().get(0).getStatistics());
    }

    @Test
    public void testIsPageProcessed() {
        results.addResult(link, stats);
        Assertions.assertTrue(results.isPageProcessed(link));
    }

    @Test
    public void testIsNotPageProcessed() {
        Assertions.assertFalse(results.isPageProcessed(link));
    }

    @Test
    public void testAddResult() {
        results.addResult(link, stats);
        Assertions.assertEquals(link, results.values().get(0).getUrl());
        Assertions.assertEquals(stats, results.values().get(0).getStatistics());
    }

    @Test
    public void testGetSortedStats() {
        for (int i = 0; i < 5; i++) {
            results.addResult(String.valueOf(i), new ArrayList<>(Arrays.asList(i, i, i)));
        }

        Assertions.assertEquals(new ArrayList<>(Arrays.asList(4, 4, 4)),
                results.getSortedStats().get(0).getStatistics());
    }
}
