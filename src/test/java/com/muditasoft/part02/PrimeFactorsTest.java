package com.muditasoft.part02;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrimeFactorsTest {

    private Map<Integer, List<Integer>> primeFactorsExpectations = new HashMap<>();

    @BeforeAll
    void setUp() {
        primeFactorsExpectations.put(1, Collections.EMPTY_LIST);
        primeFactorsExpectations.put(2, List.of(2));
        primeFactorsExpectations.put(3, List.of(3));
        primeFactorsExpectations.put(4, List.of(2, 2));
        primeFactorsExpectations.put(5, List.of(5));
        primeFactorsExpectations.put(6, List.of(2, 3));
        primeFactorsExpectations.put(7, List.of(7));
        primeFactorsExpectations.put(8, List.of(2, 2, 2));
        primeFactorsExpectations.put(9, List.of(3, 3));
    }

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

    @RepeatedTest(9)
    void generatedWithRepeatedTest(RepetitionInfo repetitionInfo) {
        assertEquals(primeFactorsExpectations.get(repetitionInfo.getCurrentRepetition()), PrimeFactors.generate(repetitionInfo.getCurrentRepetition()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void generatedWithParameterizedTest(Integer number) {
        assertEquals(primeFactorsExpectations.get(number), PrimeFactors.generate(number));
    }

    @TestFactory
    Stream<DynamicTest> generatedWithDynamicTest() {
        return Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .map(number -> dynamicTest("Generated prime factors for " + number,
                        () -> assertEquals(primeFactorsExpectations.get(number), PrimeFactors.generate(number))));
    }
}