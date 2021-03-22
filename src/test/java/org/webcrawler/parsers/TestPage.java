package org.webcrawler.parsers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.webcrawler.data.Results;
import org.webcrawler.lib.IHTMLFetcher;
import org.webcrawler.utils.Config;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestPage {
    private Config config;

    @BeforeEach
    void initialize() {
        config = new Config();
        config.setLink("somelink");
        config.setTerms(new String[]{"GET", "POST"});
    }

    @Test
    public void testRun() {
        ArrayList<Pattern> patterns = new ArrayList<>();
        for (var term : config.getTerms()) {
            patterns.add(Pattern.compile(term));
        }

        Results results = new Results();
        TestHTMLFetcher testHTMLFetcher = new TestHTMLFetcher();
        testHTMLFetcher.setLink(config.getLink());

        new Page(testHTMLFetcher).run(results, patterns, config, 1);

        Assertions.assertEquals(11, results.size());
    }

    @Test
    public void testParseLinks() {
        Assertions.assertEquals(new TestHTMLFetcher().getLinks(), new Page(new TestHTMLFetcher()).parseLinks());
    }

    @Test
    public void testCollectStatistics() {
        ArrayList<Pattern> terms = new ArrayList<>();
        terms.add(Pattern.compile("GET"));
        terms.add(Pattern.compile("POST"));
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(1, 1)), new Page(new TestHTMLFetcher()).collectStatistics(terms));
    }

    class TestHTMLFetcher implements IHTMLFetcher {
        private List<String> listLinks;
        private String body;
        private String link;

        @Override
        public void setLink(String link) {
            this.link = link;
        }

        @Override
        public String getLink() {
            return link;
        }

        @Override
        public String getBody() {
            if (body != null) return body;
            InputStream file = getClass().getClassLoader().getResourceAsStream("test.html");

            body = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            return body;
        }

        @Override
        public List<String> getLinks() {
            if (listLinks != null) return listLinks;

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("links.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));


            String line;
            listLinks = new ArrayList<>();

            try {
                while ((line = reader.readLine()) != null) {
                    listLinks.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return listLinks;
        }

        @Override
        public boolean successful() {
            return true;
        }
    }
}
