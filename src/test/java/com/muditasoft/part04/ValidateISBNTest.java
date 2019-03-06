package com.muditasoft.part04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidateISBNTest {

    @Test
    void checkAValidISBN() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("0140449116");
        assertTrue(result, "First Value");
        result = validateISBN.checkISBN("01401773961");
        assertTrue(result, "Second Value");
    }

    @Test
    void checkAnInvalidISBN() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("0140449117");
        assertFalse(result);
    }
}
