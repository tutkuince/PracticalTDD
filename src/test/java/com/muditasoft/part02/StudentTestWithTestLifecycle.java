package com.muditasoft.part02;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentTestWithTestLifecycle {

    private Student stdTutku = new Student("id1", "Tutku", "Ince");

    @BeforeAll  // Must be static when @TestInstance(TestInstance.Lifecycle.PER_CLASS) not used.
    void setUp() {

    }

    @Test
    void stateCannotChangeWhenLifecycleIsPerMethod() {
        assertEquals("Tutku", stdTutku.getName());
        stdTutku = new Student("id2", "Utku", "Ince");
    }

    @Test
    void stateCannotChangeWhenLifecycleIsPerClass() {
        assertEquals("Tutku", stdTutku.getName());
        stdTutku = new Student("id2", "Utku", "Ince");
    }
}
