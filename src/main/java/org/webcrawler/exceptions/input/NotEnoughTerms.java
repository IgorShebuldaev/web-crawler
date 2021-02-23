package org.webcrawler.exceptions.input;

public class NotEnoughTerms extends Exception {
    public NotEnoughTerms() {
        System.out.println("Need at least one term.");
        System.exit(0);
    }
}
