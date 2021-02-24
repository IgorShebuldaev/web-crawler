package org.webcrawler;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.webcrawler.parser.PageParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class TestParser {
    private PageParser pageParser;
    private String link = "https://junit.org/junit5/";

    @BeforeEach
    public void initialize() {
        pageParser = new PageParser(link);
    }

    @Test
    public void testGetDocument() throws IOException {
        assertNotNull(pageParser.getDocument(link));
    }

    @Test
    public void testParseLinks() throws IOException {
        assertNotNull(pageParser.parseLinks(pageParser.getDocument(link)));
    }

    @Test
    public void testCollectStatistics() throws IOException {
        Document document = pageParser.getDocument(link);
        ArrayList<Pattern> terms = new ArrayList<>();
        terms.add(Pattern.compile("Resources"));
        assertNotNull(pageParser.collectStatistics(document, terms));
    }
}
