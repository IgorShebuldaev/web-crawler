package org.webcrawler.parsers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCommandLine {
    private final CommandLine commandLine =
            new CommandLine(new String[]{
                    "-f", "report", "-l", "https://google.com",
                    "-d", "8", "-p", "100", "-t", "google"});

    @Test
    public void testGetConfig() {
        Assertions.assertNotNull(commandLine.getConfig());
    }
}
