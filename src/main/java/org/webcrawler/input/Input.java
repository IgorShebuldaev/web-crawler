package org.webcrawler.input;

import org.jsoup.Jsoup;
import org.webcrawler.exceptions.InvalidLink;
import org.webcrawler.exceptions.NotEnoughArguments;
import org.webcrawler.exceptions.NotEnoughTerms;

import java.util.*;

public class Input {
    private final String[] args;
    private final String flagLink = "-l", flagPageLimit = "-p", flagDepth = "-d", flagTerms = "-t";
    private String link;
    private int depth, pageLimit;
    private String[] terms = new String[0];

    public Input(String[] args) throws InvalidLink, NotEnoughTerms, NotEnoughArguments {
        this.args = args;
        searchLink();
        depth = getParameter(flagDepth);
        pageLimit = getParameter(flagPageLimit);
        searchTerms();
    }

    public String getLink() {
        return link;
    }

    public String[] getTerms() {
        return terms;
    }

    public List<Integer> getParameters() {
        return new ArrayList<>(Arrays.asList(depth, pageLimit));
    }

    private void searchLink() throws InvalidLink, NotEnoughArguments {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(flagLink)) {
                link = args[i + 1];

                try {
                    isValidLink(link);
                } catch (Exception ignored) {
                    throw new InvalidLink();
                }
            }
        }

        if (link == null) throw new NotEnoughArguments();
    }

    private int getParameter(String flag) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(flag)) {
                return Integer.parseInt(args[i + 1]);
            }
        }

        return 0;
    }

    private void searchTerms() throws NotEnoughArguments, NotEnoughTerms {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(flagTerms)) {
                try {
                    terms = Arrays.copyOfRange(args, i + 1, args.length);
                } catch (Exception e) {
                    throw new NotEnoughTerms();
                }

            }
        }

        if (terms.length == 0)
            throw new NotEnoughArguments("The terms are not found.");
    }

    private void isValidLink(String link) throws Exception {
        Jsoup.connect(link).userAgent("Mozilla/5.0").execute();
    }
}
