package com.muditasoft.part01._02calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@Tag("calculator")
public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    // Standard Assertions
    @Test
    @Tag("sumIntegers")
    void sumTwoIntegers() {
        assertEquals(2, calculator.add(1, 1));
    }

    @Test
    @Tag("multiplyIntegers")
    void multiplyTwoIntegers() {
        assertEquals(4, calculator.multiply(2, 2), "The optional failure message is now the last parameter");
        assertEquals(-2, calculator.multiply(2, -1));
        assertTrue(calculator.multiply(2, 2) == 4, () -> "Assertion messages can be lazily evaluated -- " + "to avoid constructing complex messages unnecessarily.");
    }

    // Grouped Assertions
    // In a grouped assertion all assertions are executed, and all// failures will be reported together
    @Test
    void sumAndMultiplyTwoIntegers() {
        assertAll("Sum and Multiply",
                () -> assertEquals(2, calculator.add(1, 1)),
                () -> assertEquals(2, calculator.multiply(2, 1)));
    }

    // Within a code block, if an assertion fails the// subsequent code in the same block will be skipped.
    @Test
    void multiplyTwoIntegersWhenTheNumbersIsGreaterThanZero() {
        assertAll("Numbers",
                () -> {
                    Integer number1 = 1;
                    Integer number2 = 2;

                    assertTrue(number1 > 0 && number2 > 0, "Numbers must be greater then zero!");

                    // Executed only if the previous assertion is valid
                    assertAll("Numbers",
                            () -> assertEquals(2, calculator.multiply(number1, number2)));
                });
    }

    // exceptionTesting
    @Test
    void throwsArithmeticExceptionWhenSecondNumberIsZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }

    // Assumptions
    @Test
    void testOnlyOnCiServer() {
        assumeTrue("CI".equals(System.getenv("ENV")));
        // remainder of test
    }

    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")),
                () -> "Aborting test: not on developer workstation");
        // remainder of test
    }

    @Test
    void testInAllEnvironments() {
        assumingThat("CI".equals(System.getenv("ENV")),
                () -> {
                    // perform these assertions only on the CI server
                    assertEquals(2, 2);
                });

        // perform these assertions in all environments
        assertEquals("a string", "a string");
    }

    // Disabling Test
    @Disabled("Disabled until bug #42 has been resolved")
    @Test
    void testWillBeSkipped() {

    }
}
