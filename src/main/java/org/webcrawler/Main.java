package org.webcrawler;

import org.webcrawler.data.Results;
import org.webcrawler.utils.exceptions.comandlinearguments.InvalidLink;
import org.webcrawler.utils.exceptions.comandlinearguments.NotEnoughArguments;
import org.webcrawler.utils.exceptions.page.PageLimitExceeded;
import org.webcrawler.parsers.CommandLineArguments;
import org.webcrawler.parsers.Page;
import org.webcrawler.report.Output;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws InvalidLink, NotEnoughArguments, IOException {

        CommandLineArguments arguments = new CommandLineArguments(args);

        ArrayList<Pattern> patterns = new ArrayList<>();
	    for (var term : arguments.getTerms()) {
	        patterns.add(Pattern.compile(term));
        }

        Results results = new Results();

	    try {
            new Page(arguments.getLink(), arguments.getParameters()).run(results, patterns, 1);
        } catch(PageLimitExceeded pageLimitExceeded) {
	        System.err.println("Page limit exceeded! Finishing.");
        } catch (IOException error) {
	        System.exit(1);
        }

        new Output("output.csv", arguments.getTerms(), results).makeReport();
    }
}
