package org.webcrawler.parsers;

import org.webcrawler.utils.Validator;
import org.webcrawler.utils.exceptions.comandlinearguments.InvalidLink;
import org.webcrawler.utils.exceptions.comandlinearguments.NotEnoughArguments;

import java.util.*;

public class CommandLineArguments {
    public interface ISearching {
        void searchArgument(int index);
    }

    private final String[] args;
    private final String flagLink = "-l", flagPageLimit = "-p", flagDepth = "-d", flagTerms = "-t";
    private String link;
    private int depth = 0, pageLimit = 0;
    private String[] terms;

    public CommandLineArguments(String[] args) throws NotEnoughArguments, InvalidLink {
        this.args = args;

        searchArgument(flagLink, (i) -> link = args[i + 1]);
        searchArgument(flagPageLimit, (i) -> pageLimit = Integer.parseInt(args[i + 1]));
        searchArgument(flagDepth, (i) -> depth = Integer.parseInt(args[i + 1]));
        searchArgument(flagTerms, (i) -> terms = Arrays.copyOfRange(args, i + 1, args.length));

        new Validator().validateArguments(link, terms);
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
