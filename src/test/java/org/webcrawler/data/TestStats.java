package org.webcrawler.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestStats {

    ArrayList<Integer> statistic = new ArrayList<>(Arrays.asList(1, 2, 3));
    Stats stats = new Stats("somelink", statistic);

    @Test
    public void testGetUrl() {
        assertEquals("somelink", stats.getUrl());
    }

    @Test
    public void testGetStatistics() {
        assertEquals(statistic, stats.getStatistics());
    }

    @Test
    public void testTotal() {
        assertEquals(6, stats.total());
    }
}
