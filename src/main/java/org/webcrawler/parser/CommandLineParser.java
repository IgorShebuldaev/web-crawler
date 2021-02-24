package org.webcrawler.parser;

import java.util.*;

public class CommandLineParser {
    public interface ISearching {
        void searchArgument(int index);
    }

    private final String[] args;
    private final String flagLink = "-l", flagPageLimit = "-p", flagDepth = "-d", flagTerms = "-t";
    private String link;
    private int depth = 0, pageLimit = 0;
    private String[] terms = new String[0];

    public CommandLineParser(String[] args) {
        this.args = args;

        searchArgument(flagLink, (i) -> link = args[i + 1]);
        searchArgument(flagPageLimit, (i) -> pageLimit = Integer.parseInt(args[i + 1]));
        searchArgument(flagDepth, (i) -> depth = Integer.parseInt(args[i + 1]));
        searchArgument(flagTerms, (i) -> terms = Arrays.copyOfRange(args, i + 1, args.length));
    }

    public String getLink() {
        return link;
    }

    public List<Integer> getParameters() {
        return new ArrayList<>(Arrays.asList(depth, pageLimit));
    }

    public String[] getTerms() {
        return terms;
    }

    void searchArgument(String flag, ISearching searching) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(flag)) {
                searching.searchArgument(i);
            }
        }
    }
}
