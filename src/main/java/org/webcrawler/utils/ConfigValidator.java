package org.webcrawler.utils;

import org.webcrawler.lib.IHTMLFetcher;

public class ConfigValidator {
    private boolean checked = false;
    private Config config;
    private Errors errors;
    private IHTMLFetcher HTMLFetcher;

    public ConfigValidator(Config config, IHTMLFetcher HTMLFetcher) {
        this.config = config;
        this.HTMLFetcher = HTMLFetcher;
    }

    public boolean isValid() {
        if (!checked) validateArguments();

        return errors.isEmpty();
    }

    public String getErrors() {
        return errors.fullMessages();
    }

    private void validateArguments() {
        errors = new Errors();
        validateLink();
        validateTerms();
        checked = true;
    }

    private void validateLink() {
       if (!HTMLFetcher.successful()) {
            errors.addError("Invalid link");
       }
    }

    private void validateTerms()  {
        if (config.getTerms() == null) {
            errors.addError("Not enough arguments");
        }
    }
}
