package org.webcrawler;

import org.junit.jupiter.api.BeforeEach;
import org.webcrawler.Results;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestResults {
    private Results results;

    @BeforeEach
    public void initialize() {
        results = new Results();
    }

    @Test
    public void testIsPageProcessed() {
        String link = "https://google.com";
        assertFalse(results.isPageProcessed(link));

        results.addResult(link, new ArrayList<>());
        assertTrue(results.isPageProcessed(link));
    }

    @Test
    public void testGetSortedStats() {
        assertNotNull(results.getSortedStats());
    }

    @Test
    public void testValues() {
        assertNotNull(results.values());
    }
}
