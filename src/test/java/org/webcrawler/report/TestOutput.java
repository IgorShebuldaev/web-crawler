package org.webcrawler.report;

import org.junit.jupiter.api.*;
import org.webcrawler.data.Results;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class TestOutput {
    private Results results;
    private Output output;
    private String[] headers = new String[]{"Tesla", "Elon", "Elon Mask"};
    private String link = "https://google.com";
    private String filename = "temp.csv";

    @BeforeEach
    public void initialize() throws IOException {
        results = new Results();
        results.addResult(link, new ArrayList<>(Arrays.asList(1, 2, 3)));
        output = new Output(filename, headers, results);
    }

    @AfterEach
    public void clean() throws IOException {
        Files.deleteIfExists(Paths.get(filename));
    }

    @Test
    public void testMakeReport() throws IOException {
        output.makeReport();

        String content = Files.readString(Paths.get(filename));
        String expected = "\"Url\",\"Tesla\",\"Elon\",\"Elon Mask\",\n\"https://google.com\",\"1\",\"2\",\"3\",\n";
        Assertions.assertEquals(expected, content);
    }

    @Test
    public void testWriteHeader() throws IOException {
        output.writeHeader(new ArrayList<>(Arrays.asList(headers)));
        output.finish();

        String content = Files.readString(Paths.get(filename));
        Assertions.assertEquals("\"Tesla\",\"Elon\",\"Elon Mask\",\n", content);
    }

    @Test
    public void testWriteResults() throws IOException {
        output.writeResults(results.values());
        output.finish();

        String content = Files.readString(Paths.get(filename));
        Assertions.assertEquals("\"https://google.com\",\"1\",\"2\",\"3\",\n", content);
    }

    @Test
    public void testWriteResultsWithLimit() throws IOException {
        for (int i = 0; i < 5; i++) {
            results.addResult(link, new ArrayList<>(Arrays.asList(1, 2, 3)));
        }

        output.writeResults(results.values(), 1);
        output.finish();


        long lines = Files.lines(Paths.get(filename)).count();
        Assertions.assertEquals(1, lines);
    }

    @Test
    public void testFinish() throws IOException {
        output.finish();
        Assertions.assertThrows(IOException.class, () ->output.writeHeader(new ArrayList<>(Arrays.asList(headers))));
    }


}
