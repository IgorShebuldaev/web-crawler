package org.webcrawler;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class TestParser {
    private Parser parser;
    private String link = "https://junit.org/junit5/";

    @BeforeEach
    public void initialize() {
        parser = new Parser(link);
    }

    @Test
    public void testGetDocument() throws IOException {
        assertNotNull(parser.getDocument(link));
    }

    @Test
    public void testParseLinks() throws IOException {
        assertNotNull(parser.parseLinks(parser.getDocument(link)));
    }

    @Test
    public void testCollectStatistics() throws IOException {
        Document document = parser.getDocument(link);
        ArrayList<Pattern> terms = new ArrayList<>();
        terms.add(Pattern.compile("Resources"));
        assertNotNull(parser.collectStatistics(document, terms));
    }
}
