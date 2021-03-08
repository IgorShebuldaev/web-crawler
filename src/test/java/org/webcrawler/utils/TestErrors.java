package org.webcrawler.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestErrors {
    Errors errors = new Errors();

    @Test
    public void testAddErrorAndFullMessages() {
        errors.addError("error 1");
        errors.addError("error 2");
        Assertions.assertEquals("error 1, error 2", errors.fullMessages());
    }

    @Test
    public void testIsEmpty() {
        Assertions.assertTrue(errors.isEmpty());
    }

    @Test
    public void testIsNotEmpty() {
        errors.addError("error1");
        Assertions.assertFalse(errors.isEmpty());
    }
}
