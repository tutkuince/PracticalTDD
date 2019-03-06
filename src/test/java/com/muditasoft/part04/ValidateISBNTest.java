package com.muditasoft.part04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateISBNTest {

    private ValidateISBN validateISBN;

    @BeforeEach
    void setUp() {
        validateISBN = new ValidateISBN();
    }

    @Test
    void checkAValid10DigitISBN() {

        boolean result = validateISBN.checkISBN("0140449116");
        assertTrue(result, "First Value");

        result = validateISBN.checkISBN("0140449116");
        assertTrue(result, "Second Value");
    }

    @Test
    void checkAValid13DigitISBN() {
        boolean result = validateISBN.checkISBN("9780486432151");
        assertTrue(result, "First Value");
    }

    @Test
    void checkAnInvalid13DigitISBN() {
        boolean result = validateISBN.checkISBN("9780486432152");
        assertFalse(result, "First Value");
    }

    @Test
    void tenDigitISBNNumbersEndingInAnXAreValid() {
        boolean result = validateISBN.checkISBN("007462542X");
        assertTrue(result);
    }

    @Test
    void checkAnInvalid10DigitISBN() {
        boolean result = validateISBN.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test
    void nineDigitISBNsAreNotAllowed() {
        assertThrows(NumberFormatException.class, () -> validateISBN.checkISBN("140449116"));
    }

    @Test
    void nonNumericISBNsAreNotAllowed() {
        assertThrows(NumberFormatException.class, () -> validateISBN.checkISBN("helloworld"));
    }

}
