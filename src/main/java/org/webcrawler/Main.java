package org.webcrawler;

import org.webcrawler.data.Results;
import org.webcrawler.exceptions.input.InvalidLink;
import org.webcrawler.exceptions.input.NotEnoughArguments;
import org.webcrawler.exceptions.input.NotEnoughTerms;
import org.webcrawler.exceptions.parser.PageLimitExceeded;
import org.webcrawler.parser.CommandLineParser;
import org.webcrawler.parser.PageParser;
import org.webcrawler.report.Output;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws InvalidLink, NotEnoughTerms, NotEnoughArguments, IOException {

        CommandLineParser commandLineParser = new CommandLineParser(args);

        ArrayList<Pattern> patterns = new ArrayList<>();
	    for (var term : commandLineParser.getTerms()) {
	        patterns.add(Pattern.compile(term));
        }

        Results results = new Results();

	    try {
            new PageParser(commandLineParser.getLink(), commandLineParser.getParameters()).run(results, patterns, 1);
        } catch(PageLimitExceeded pageLimitExceeded) {
	        System.err.println("Page limit exceeded! Finishing.");
        } catch (IOException error) {
	        System.exit(1);
        }

        new Output("output.csv", commandLineParser.getTerms(), results).makeReport();
    }
}
