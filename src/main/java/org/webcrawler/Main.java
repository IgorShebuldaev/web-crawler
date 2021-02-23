package org.webcrawler;

import org.webcrawler.data.Results;
import org.webcrawler.exceptions.input.InvalidLink;
import org.webcrawler.exceptions.input.NotEnoughArguments;
import org.webcrawler.exceptions.input.NotEnoughTerms;
import org.webcrawler.exceptions.parser.PageLimitExceeded;
import org.webcrawler.input.Input;
import org.webcrawler.output.Output;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws InvalidLink, NotEnoughTerms, NotEnoughArguments {

        Input input = new Input(args);

        ArrayList<Pattern> patterns = new ArrayList<>();
	    for (var term : input.getTerms()) {
	        patterns.add(Pattern.compile(term));
        }

        Results results = new Results(input.getParameters());

	    try {
            new Parser(input.getLink()).run(results, patterns, 1);
        } catch(PageLimitExceeded a) {
	        System.err.println("Page limit exceeded! Finishing.");
        } catch (IOException c) {
	        System.exit(1);
        }

        try {
            Output outputAll = new Output("output.csv");
            Output output10 = new Output("top_10.csv");

            String[] headers = new String[1 + patterns.size()];
            headers[0] = "Url";
            for(int i = 0; i < patterns.size(); i++) {
                headers[i + 1] = patterns.get(i).toString();
            }

            outputAll.writeHeader(headers);
            output10.writeHeader(headers);

            outputAll.writeResults(results.values());
            output10.writeResults(results.getSortedStats(), 10);
            output10.printTopResults(results.getSortedStats(), 10);

            outputAll.finish();
            output10.finish();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
