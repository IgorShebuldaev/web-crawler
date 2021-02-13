package org.webcrawler.exceptions;

public class NotEnoughTerms extends Exception {
    public NotEnoughTerms() {
        System.out.println("Need at least one term.");
        System.exit(0);
    }
}
