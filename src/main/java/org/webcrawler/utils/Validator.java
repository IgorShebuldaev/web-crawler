package org.webcrawler.utils;

import org.jsoup.Jsoup;
import org.webcrawler.utils.exceptions.comandlinearguments.InvalidLink;
import org.webcrawler.utils.exceptions.comandlinearguments.NotEnoughArguments;

import java.io.IOException;

public class Validator {

    public void validateArguments(String link, String[] terms) throws InvalidLink, NotEnoughArguments {
        validateLink(link);
        validateTerms(terms);
    }

    public void validateLink(String link) throws InvalidLink {
        if (link != null) {
            try {
                Jsoup.connect(link).userAgent("Mozilla/5.0").execute();
            } catch (IllegalArgumentException | IOException exception) {
                throw new InvalidLink();
            }
        }
    }

    public void validateTerms(String[] terms) throws NotEnoughArguments {
        if (terms == null) {
            throw new NotEnoughArguments();
        }
    }
}
