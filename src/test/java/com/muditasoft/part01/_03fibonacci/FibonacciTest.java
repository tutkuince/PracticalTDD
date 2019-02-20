package com.muditasoft.part01._03fibonacci;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FibonacciTest {

    @Test
    @DisplayName("Find Fibonacci Numbers For Specific Orders")
    void findFibonacciNumbers() {
        Fibonacci fibonacci = new Fibonacci();
        // 1 1 2 3 5 8
        assertThrows(IllegalArgumentException.class, () -> fibonacci.find(0));

        assertAll("Fibonacci Numbers",
                () -> assertEquals(1, fibonacci.find(1)),
                () -> assertEquals(1, fibonacci.find(2)),
                () -> assertEquals(2, fibonacci.find(3)),
                () -> assertEquals(3, fibonacci.find(4))
                );
    }
}
