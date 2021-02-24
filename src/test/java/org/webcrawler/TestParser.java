package org.webcrawler;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.webcrawler.parsers.Page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class TestParser {
    private Page page;
    private String link = "https://junit.org/junit5/";

    @BeforeEach
    public void initialize() {
        page = new Page(link, new ArrayList<>(Arrays.asList(0, 0)));
    }

    @Test
    public void testGetDocument() throws IOException {
        assertNotNull(page.getDocument(link));
    }

    @Test
    public void testParseLinks() throws IOException {
        assertNotNull(page.parseLinks(page.getDocument(link)));
    }

    @Test
    public void testCollectStatistics() throws IOException {
        Document document = page.getDocument(link);
        ArrayList<Pattern> terms = new ArrayList<>();
        terms.add(Pattern.compile("Resources"));
        assertNotNull(page.collectStatistics(document, terms));
    }
}
