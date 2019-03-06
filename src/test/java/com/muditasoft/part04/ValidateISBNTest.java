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
        assertAll("Check Valid 10 Digit ISBN",
                () -> assertTrue(validateISBN.checkISBN("0140449116")),
                () -> assertTrue(validateISBN.checkISBN("1617294942")));
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
