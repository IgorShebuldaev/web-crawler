package org.webcrawler.parsers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.webcrawler.data.Results;
import org.webcrawler.lib.IService;
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
        config.setTerms(new String[]{"html"});
    }

//infinite loop
//    @Test
//    public void testRun() {
//        ArrayList<Pattern> patterns = new ArrayList<>();
//        for (var term : config.getTerms()) {
//            patterns.add(Pattern.compile(term));
//        }
//
//        Results results = new Results();
//
//        new Page(new TestService()).run(results, patterns, config, 1);
//
//        Assertions.assertEquals(1, results.size());
//    }

    @Test
    public void testParseLinks() {
        Assertions.assertEquals(new TestService().getLinks(), new Page(new TestService()).parseLinks());
    }

    @Test
    public void testCollectStatistics() {
        ArrayList<Pattern> terms = new ArrayList<>();
        terms.add(Pattern.compile("GET"));
        terms.add(Pattern.compile("POST"));
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(1, 1)), new Page(new TestService()).collectStatistics(terms));
    }

    class TestService implements IService {

        @Override
        public void setLink(String link) {

        }

        @Override
        public String getLink() {
            return null;
        }

        @Override
        public String getBody() {
            InputStream file = getClass().getClassLoader().getResourceAsStream("test.html");

            return new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
        }

        @Override
        public List<String> getLinks() {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("links.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));


            String line;
            List<String> expectedList = new ArrayList<>();

            try {
                while ((line = reader.readLine()) != null) {
                    expectedList.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return expectedList;
        }

        @Override
        public boolean successful() {
            return true;
        }
    }
}
