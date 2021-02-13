package org.webcrawler.exceptions;

public class InvalidLink extends Exception {
    public InvalidLink() {
        System.out.println("Invalid link.");
        System.exit(0);
    }
}
