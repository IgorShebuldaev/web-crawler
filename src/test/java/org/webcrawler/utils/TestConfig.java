package org.webcrawler.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestConfig {
    private Config config;

    @BeforeEach
    public void initialize() {
        config = new Config();
        config.setFileName("report");
        config.setLink("link");
        config.setTerms(new String[]{"terms 1", "terms 2"});

    }

    @Test
    public void testSetFileName() {
        config.setFileName("somename");
        Assertions.assertEquals("somename.csv", config.getFileName());
    }

    @Test
    public void testSetLink() {
        config.setLink("somelink");
        Assertions.assertEquals("somelink", config.getLink());
    }

    @Test
    public void testSetDepth() {
        config.setDepth(5);
        Assertions.assertEquals(5, config.getDepth());
    }

    @Test
    public void testSetPageLimit() {
        config.setPageLimit(100);
        Assertions.assertEquals(100, config.getPageLimit());
    }

    @Test
    public void testSetTerms() {
        config.setTerms(new String[]{"google"});
        Assertions.assertArrayEquals(new String[]{"google"}, config.getTerms());
    }
}
