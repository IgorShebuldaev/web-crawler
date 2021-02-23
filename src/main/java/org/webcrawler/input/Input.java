package org.webcrawler.input;

import org.jsoup.Jsoup;
import org.webcrawler.exceptions.input.InvalidLink;
import org.webcrawler.exceptions.input.NotEnoughArguments;
import org.webcrawler.exceptions.input.NotEnoughTerms;

import java.util.*;

public class Input {
    public interface ISomeClass {
        void searchArguments(int index);
    }

    public class Test {
        String[] args;
        Test(String[] args) {
            this.args = args;
        }
        void searchArguments(String flag, ISomeClass is) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals(flag)) {
                    is.searchArguments(i);
                }
            }
        }
    }

    private final String[] args;
    private final String flagLink = "-l", flagPageLimit = "-p", flagDepth = "-d", flagTerms = "-t";
    private String link;
    private int depth = 0, pageLimit = 0;
    private String[] terms = new String[0];

    public Input(String[] args) throws InvalidLink, NotEnoughTerms, NotEnoughArguments {
        this.args = args;
        setLink();
        setParameters();
        setTerms();

        Test t = new Test(args);


        t.searchArguments(flagDepth, (i) -> {
            depth = Integer.parseInt(args[i + 1]);
        });

        t.searchArguments(flagPageLimit, (i) -> {
            pageLimit = Integer.parseInt(args[i + 1]);
        });

        t.searchArguments(flagLink, (i) -> {
            link = args[i + 1];
        });
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

    private void setLink() throws InvalidLink, NotEnoughArguments {
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

    private void setParameters() {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(flagPageLimit)) {
                pageLimit = Integer.parseInt(args[i + 1]);
            }

            if (args[i].equals(flagDepth)) {
                depth = Integer.parseInt(args[i + 1]);
            }
        }
    }

    private void setTerms() throws NotEnoughArguments, NotEnoughTerms {
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
