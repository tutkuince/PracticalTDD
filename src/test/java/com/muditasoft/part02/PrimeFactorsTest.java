package com.muditasoft.part02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrimeFactorsTest {

    @Test
    @DisplayName("Generate Prime Factors")
    void generateWithStandardTest() {
        assertEquals(Collections.EMPTY_LIST, PrimeFactors.generate(1));
        assertEquals(List.of(2), PrimeFactors.generate(2));
        assertEquals(List.of(3), PrimeFactors.generate(3));
        assertEquals(List.of(2, 2), PrimeFactors.generate(4));
        assertEquals(List.of(5), PrimeFactors.generate(5));
        assertEquals(List.of(2, 3), PrimeFactors.generate(6));
        assertEquals(List.of(7), PrimeFactors.generate(7));
        assertEquals(List.of(2, 2, 2), PrimeFactors.generate(8));
        assertEquals(List.of(3, 3), PrimeFactors.generate(9));
    }
}