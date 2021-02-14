package org.webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.webcrawler.data.Results;
import org.webcrawler.exceptions.PageLimitExceeded;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {
    private String link;

    public Parser(String link) {
        this.link = link;
    }

    public void run(Results results, ArrayList<Pattern> terms, int currentDepth) throws PageLimitExceeded, IOException, IllegalArgumentException {
        Document document = getDocument(link);

        results.addResult(link, collectStatistics(document, terms));

        if (currentDepth > results.getDepth()) return;
        List<String> listLinks = parseLinks(document);

        for (String nextLink : listLinks) {
            if (!results.isPageProcessed(nextLink)) {
                try {
                    new Parser(nextLink).run(results, terms, currentDepth + 1);
                } catch (Exception e) {

                }
            }

            if (results.isLimitExceeded()) {
                throw new PageLimitExceeded();
            }
        }
    }

    public Document getDocument(String link) throws IOException, IllegalArgumentException {
        return Jsoup.connect(link).userAgent("Mozilla/5.0").get();
    }

    public List<String> parseLinks(Document page) {
        List<String> linksList = new ArrayList<>();

        Elements links = page.select("a[href]");

        for (Element link : links) {
            String line = link.attr("abs:href");

            if (!(line.contains("?") || line.contains("&"))) {
                if (line.contains("#")) {
                    linksList.add(line.substring(0, line.indexOf("#")));
                } else {
                    linksList.add(line);
                }
            }
        }

        return linksList.stream().distinct().collect(Collectors.toList());
    }

    public ArrayList<Integer> collectStatistics(Document document, ArrayList<Pattern> terms) {
        return terms.stream().map((term) -> {
            Matcher matcher = term.matcher(document.body().text());
            long matches = matcher.results().count();
            return (int) matches;
        }).collect(Collectors.toCollection(ArrayList::new));
    }
}
