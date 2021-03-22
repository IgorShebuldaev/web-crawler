package org.webcrawler;

import org.webcrawler.data.Results;
import org.webcrawler.parsers.CommandLine;
import org.webcrawler.lib.HTMLFetcher;
import org.webcrawler.utils.Config;
import org.webcrawler.utils.ConfigValidator;
import org.webcrawler.parsers.Page;
import org.webcrawler.report.Output;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {

        Config config = new CommandLine(args).getConfig();

        HTMLFetcher HTMLFetcher = new HTMLFetcher();
        HTMLFetcher.setLink(config.getLink());

        ConfigValidator configValidator = new ConfigValidator(config, HTMLFetcher);

        if (!configValidator.isValid()) {
            System.out.println(configValidator.getErrors());
            System.exit(1);
        }

        ArrayList<Pattern> patterns = new ArrayList<>();
	    for (var term : config.getTerms()) {
	        patterns.add(Pattern.compile(term));
        }

        Results results = new Results();

	    new Page(HTMLFetcher).run(results, patterns, config,1);

        new Output(config.getFileName(), config.getTerms(), results).makeReport();

        System.out.println("Done!");
    }
}
