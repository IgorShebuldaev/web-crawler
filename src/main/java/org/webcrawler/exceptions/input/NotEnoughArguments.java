package org.webcrawler.exceptions.input;

public class NotEnoughArguments extends Exception {
    public NotEnoughArguments() {
        System.out.println("The start link was not found.");
        System.exit(0);
    }

    public NotEnoughArguments(String message) {
        System.out.println(message);
        System.exit(0);
    }
}
