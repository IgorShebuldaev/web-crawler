package org.webcrawler.utils;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Errors {
    private ArrayList<String> errors = new ArrayList<>();

    public boolean isEmpty() {
        return errors.isEmpty();
    }

    public String fullMessages() {
        if (errors.isEmpty()) return "";
        return fullMessages(", ");
    }

    public String fullMessages(CharSequence delimiter) {
        StringJoiner joiner = new StringJoiner(delimiter);
        errors.forEach(joiner::add);

        return joiner.toString();
    }

    public void addError(String error) {
        errors.add(error);
    }
}
