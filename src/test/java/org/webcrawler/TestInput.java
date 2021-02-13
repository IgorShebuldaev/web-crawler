package org.webcrawler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.webcrawler.exceptions.InvalidLink;
import org.webcrawler.exceptions.NotEnoughArguments;
import org.webcrawler.exceptions.NotEnoughTerms;
import org.webcrawler.input.Input;

import java.util.ArrayList;
import java.util.Arrays;

public class TestInput {
    private String[] args;
    private Input input;

    @BeforeEach
    public void initialize() throws NotEnoughArguments, InvalidLink, NotEnoughTerms {
        args = new String[]{"-l", "https://google.com", "-t", "google"};
        input = new Input(args);
    }

    @Test
    public void testGetLink() {
        assertEquals("https://google.com", input.getLink());
    }

    @Test
    public void testGetTerms() {
        assertArrayEquals(new String[]{"google"}, input.getTerms());
    }

    @Test
    public void testGetParameters() {
        assertEquals(new ArrayList<>(Arrays.asList(0, 0)), input.getParameters());
    }
}
