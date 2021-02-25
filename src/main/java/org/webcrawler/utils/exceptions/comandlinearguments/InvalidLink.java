package org.webcrawler.utils.exceptions.comandlinearguments;

public class InvalidLink extends Exception {
    public InvalidLink() {
        System.out.println("Invalid link.");
        System.exit(0);
    }
}
