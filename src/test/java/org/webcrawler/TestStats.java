package org.webcrawler;

import org.junit.jupiter.api.Test;
import org.webcrawler.data.Stats;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestStats {

    @Test
    public void testTotal() {
        Stats stats = new Stats("https://google.com", new ArrayList<>());
        assertEquals(0, stats.total());
    }
}
