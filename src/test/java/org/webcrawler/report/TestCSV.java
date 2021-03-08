package org.webcrawler.report;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestCSV {
    private static String filename = "temp.csv";
    private CSV csv = new CSV(filename);

    public TestCSV() throws IOException {
    }

    @AfterAll
    static void clean() throws IOException {
        Files.deleteIfExists(Paths.get(filename));
    }

    @Test
    public void testWrite() throws IOException {
        csv.write("word");
        csv.close();

        String content = Files.readString(Paths.get(filename));
        Assertions.assertEquals("\"word\",", content);
    }

    @Test
    public void testNewLine() throws IOException {
        csv.newLine();
        csv.close();

        String content = Files.readString(Paths.get(filename));
        Assertions.assertEquals("\n", content);
    }

    @Test
    public void testClose() throws IOException {
        csv.close();
        Assertions.assertThrows(IOException.class, () -> csv.write("word"));
    }
}
