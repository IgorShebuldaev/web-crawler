package org.webcrawler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.webcrawler.data.Stats;
import org.webcrawler.output.Output;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestOutput {
    private Output output;
    private ArrayList<Stats> stats;
    private String[] headers;

    @BeforeEach
    public void initialize() throws IOException {
        headers = new String[]{"Tesla", "Elon", "Elon Mask"};
        stats = new ArrayList<>();
        output = new Output("somename");

        for(int i = 0; i < 10; i++)
            stats.add(new Stats("https://google.com", new ArrayList<>(Arrays.asList(0, 2, 3, 4))));
    }

    @Test
    public void testWriteHeader() {
        assertDoesNotThrow(() -> output.writeHeader(headers));
    }

    @Test
    public void testWriteResults() {
        assertDoesNotThrow(() -> output.writeResults(stats));
        assertDoesNotThrow(() -> output.writeResults(stats, 10));
    }
}
