package org.webcrawler;

import org.webcrawler.data.Results;
import org.webcrawler.exceptions.input.InvalidLink;
import org.webcrawler.exceptions.input.NotEnoughArguments;
import org.webcrawler.exceptions.input.NotEnoughTerms;
import org.webcrawler.exceptions.parser.PageLimitExceeded;
import org.webcrawler.parsers.CommandLineArguments;
import org.webcrawler.parsers.Page;
import org.webcrawler.report.Output;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws InvalidLink, NotEnoughTerms, NotEnoughArguments, IOException {

        CommandLineArguments commandLineArguments = new CommandLineArguments(args);

        ArrayList<Pattern> patterns = new ArrayList<>();
	    for (var term : commandLineArguments.getTerms()) {
	        patterns.add(Pattern.compile(term));
        }

        Results results = new Results();

	    try {
            new Page(commandLineArguments.getLink(), commandLineArguments.getParameters()).run(results, patterns, 1);
        } catch(PageLimitExceeded pageLimitExceeded) {
	        System.err.println("Page limit exceeded! Finishing.");
        } catch (IOException error) {
	        System.exit(1);
        }

        new Output("output.csv", commandLineArguments.getTerms(), results).makeReport();
    }
}
