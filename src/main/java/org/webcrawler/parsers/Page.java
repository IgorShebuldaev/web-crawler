package org.webcrawler.parsers;

import org.webcrawler.data.Results;
import org.webcrawler.lib.IService;
import org.webcrawler.utils.Config;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Page {
    private IService service;

    public Page(IService service) {
        this.service = service;
    }

    public void run(Results results, ArrayList<Pattern> terms, Config config, int currentDepth) {
        if(!service.successful()) return;

        results.addResult(service.getLink(), collectStatistics(terms));

        if (currentDepth > config.getDepth()) return;
        List<String> listLinks = parseLinks();

        for (String nextLink : listLinks) {
            if (!results.isPageProcessed(nextLink)) {
                service.setLink(nextLink);
                new Page(service).run(results, terms, config, currentDepth + 1);
            }

            if (results.size() >= config.getPageLimit()) {
                break;
            }
        }
    }

    public List<String> parseLinks() {
        List<String> linksList = new ArrayList<>();

        for (String link : service.getLinks()) {

            if (!(link.contains("?") || link.contains("&"))) {
                if (link.contains("#")) {
                    linksList.add(link.substring(0, link.indexOf("#")));
                } else {
                    linksList.add(link);
                }
            }
        }

        return linksList.stream().distinct().collect(Collectors.toList());
    }

    public ArrayList<Integer> collectStatistics(ArrayList<Pattern> terms) {
        return terms.stream().map((term) -> {
            Matcher matcher = term.matcher(service.getBody());
            long matches = matcher.results().count();
            return (int) matches;
        }).collect(Collectors.toCollection(ArrayList::new));
    }
}
