package org.webcrawler;

import org.webcrawler.exceptions.PageLimitExceeded;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
	    if (args.length < 2) {
	        System.err.println("Not enough arguments");
	        System.exit(1);
        }

        Results results = new Results();

        ArrayList<Pattern> patterns = new ArrayList<>();
	    for (int i = 1; i < args.length; i++) {
	        patterns.add(Pattern.compile(args[i]));
        }

	    try {
            new Parser(args[0]).run(results, patterns, 1);
        } catch(PageLimitExceeded _e) {
	        System.err.println("Page limit exceeded! Finishing.");
        } catch (IllegalArgumentException e) {
            System.out.println("Bad URL");
        } catch (IOException e) {
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
            output10.writeResults(results.getSortedStats(), 9);
            output10.printTopResults(results.getSortedStats());

            outputAll.finish();
            output10.finish();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
