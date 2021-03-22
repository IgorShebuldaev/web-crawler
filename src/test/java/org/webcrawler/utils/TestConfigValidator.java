package org.webcrawler.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.webcrawler.lib.IHTMLFetcher;

import java.util.List;

public class TestConfigValidator {
    Config validConfig;

    @BeforeEach
    void initialize() {
        validConfig = new Config();
        validConfig.setLink("somesite");
        validConfig.setTerms(new String[]{"term"});
    }

    @Test
    public void testIsValid() {
        TestHTMLFetcher testHTMLFetcher = new TestHTMLFetcher();
        testHTMLFetcher.setLink(validConfig.getLink());
        Assertions.assertTrue(new ConfigValidator(validConfig, testHTMLFetcher).isValid());
    }

    @Test
    public void testIsInvalid() {
        Assertions.assertFalse(new ConfigValidator(new Config(), new TestHTMLFetcher()).isValid());
    }

    @Test
    public void testGetErrors() {
        ConfigValidator configValidator = new ConfigValidator(new Config(), new TestHTMLFetcher());
        configValidator.isValid();
        Assertions.assertEquals("Invalid link, Not enough arguments", configValidator.getErrors());
    }

    @Test
    public void testGetErrorsIfValidConfig() {
        TestHTMLFetcher testHTMLFetcher = new TestHTMLFetcher();
        testHTMLFetcher.setLink("somelink");
        ConfigValidator configValidator = new ConfigValidator(validConfig, testHTMLFetcher);
        configValidator.isValid();
        Assertions.assertEquals("", configValidator.getErrors());
    }

    public class TestHTMLFetcher implements IHTMLFetcher {
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
