package com.muditasoft.part04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateISBNTest {

    @Test
    void checkAValidISBN() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("0140449116");
        assertTrue(result, "First Value");

        result = validateISBN.checkISBN("0140449116");
        assertTrue(result, "Second Value");
    }

    @Test
    void ISBNNumbersEndingInAnXAreValid() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("007462542X");
        assertTrue(result);
    }

    @Test
    void checkAnInvalidISBN() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test
    void nineDigitISBNsAreNotAllowed() {
        ValidateISBN validateISBN = new ValidateISBN();
        assertThrows(NumberFormatException.class, () -> validateISBN.checkISBN("140449116"));
    }

    @Test
    void nonNumericISBNsAreNotAllowed() {
        ValidateISBN validateISBN = new ValidateISBN();
        assertThrows(NumberFormatException.class, () -> validateISBN.checkISBN("helloworld"));
    }

}
