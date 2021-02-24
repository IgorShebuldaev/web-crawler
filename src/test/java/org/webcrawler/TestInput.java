package org.webcrawler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.webcrawler.exceptions.input.InvalidLink;
import org.webcrawler.exceptions.input.NotEnoughArguments;
import org.webcrawler.exceptions.input.NotEnoughTerms;
import org.webcrawler.parsers.CommandLineArguments;

import java.util.ArrayList;
import java.util.Arrays;

public class TestInput {
    private String[] args;
    private CommandLineArguments commandLineArguments;

    @BeforeEach
    public void initialize() throws NotEnoughArguments, InvalidLink, NotEnoughTerms {
        args = new String[]{"-l", "https://google.com", "-t", "google"};
        commandLineArguments = new CommandLineArguments(args);
    }

    @Test
    public void testGetLink() {
        assertEquals("https://google.com", commandLineArguments.getLink());
    }

    @Test
    public void testGetTerms() {
        assertArrayEquals(new String[]{"google"}, commandLineArguments.getTerms());
    }

    @Test
    public void testGetParameters() {
        assertEquals(new ArrayList<>(Arrays.asList(0, 0)), commandLineArguments.getParameters());
    }
}
