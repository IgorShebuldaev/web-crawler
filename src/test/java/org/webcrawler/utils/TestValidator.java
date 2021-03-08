package org.webcrawler.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.webcrawler.lib.IService;

import java.util.List;

public class TestValidator {
    Config validConfig;

    @BeforeEach
    void initialize() {
        validConfig = new Config();
        validConfig.setLink("somesite");
        validConfig.setTerms(new String[]{"term"});
    }

    @Test
    public void testIsValid() {
        TestService testService = new TestService();
        testService.setLink(validConfig.getLink());
        Assertions.assertTrue(new Validator(validConfig, testService).isValid());
    }

    @Test
    public void testIsInvalid() {
        Assertions.assertFalse(new Validator(new Config(), new TestService()).isValid());
    }

    @Test
    public void testGetErrors() {
        Validator validator = new Validator(new Config(), new TestService());
        validator.isValid();
        Assertions.assertEquals("Invalid link, Not enough arguments", validator.getErrors());
    }

    @Test
    public void testGetErrorsIfValidConfig() {
        TestService testService = new TestService();
        testService.setLink("somelink");
        Validator validator = new Validator(validConfig, testService);
        validator.isValid();
        Assertions.assertEquals("", validator.getErrors());
    }

    public class TestService implements IService {
        String link;

        @Override
        public void setLink(String link) {
            this.link = link;
        }

        @Override
        public String getLink() {
            return link;
        }

        @Override
        public String getBody() {
            return null;
        }

        @Override
        public List<String> getLinks() {
            return null;
        }

        @Override
        public boolean successful() {
            return link != null;
        }
    }
}
