package org.webcrawler.utils.exceptions.comandlinearguments;

public class NotEnoughArguments extends Exception {
    public NotEnoughArguments() {
        System.out.println("Terms are not found.");
        System.exit(0);
    }
}
