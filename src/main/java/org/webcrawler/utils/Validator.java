package org.webcrawler.utils;

import org.webcrawler.lib.IService;

public class Validator {
    private Config config;
    private Errors errors;
    private boolean checked = false;
    private IService service;

    public Validator(Config config, IService service) {
        this.config = config;
        this.service = service;
    }

    public boolean isValid() {
        if (!checked) validateArguments();

        return isEmpty();
    }

    public String getErrors() {
        return errors.fullMessages();
    }

    private boolean isEmpty() {
        return errors.isEmpty();
    }

    private void validateArguments() {
        errors = new Errors();
        validateLink();
        validateTerms();
        checked = true;
    }

    private void validateLink() {
       if (!service.successful()) {
            errors.addError("Invalid link");
       }
    }

    private void validateTerms()  {
        if (config.getTerms() == null) {
            errors.addError("Not enough arguments");
        }
    }
}
