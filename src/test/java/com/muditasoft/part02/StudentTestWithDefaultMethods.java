package com.muditasoft.part02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTestWithDefaultMethods implements CreateDomain<Student>, TestLifecycleReporter {

    @Override
    public Student createDomain() {
        return new Student("id1", "Tutku", "Ince");
    }

    @Test
    void createStudent() {
        final Student student = createDomain();

        assertAll("Student",
                () -> assertEquals("id1", student.getId()),
                () -> assertEquals("Tutku", student.getName()),
                () -> assertEquals("Ince", student.getSurname()));
    }
}
