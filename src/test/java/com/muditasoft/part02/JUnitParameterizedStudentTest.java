package com.muditasoft.part02;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Student Test with TestInfo and TestReporter Parameters")
public class JUnitParameterizedStudentTest {

    private Student student;

    public JUnitParameterizedStudentTest(TestInfo testInfo) {
        assertEquals("Student Test with TestInfo and TestReporter Parameters", testInfo.getDisplayName());
    }

    @BeforeEach
    void setStudent(TestInfo testInfo) {
        if (testInfo.getTags().contains("create"))
            student = new Student("id1", "Tutku", "Ince");
        else
            student = new Student("id2", "Emin", "Koklu");
    }

    @Test
    @DisplayName("Create Student")
    @Tag("create")
    void createStudent() {
        assertEquals("Tutku", student.getName());
    }

    @Test
    @DisplayName("Add Course to Student")
    @Tag("addCourse")
    void addCourseToStudent(TestReporter testReporter) {
        testReporter.publishEntry("startTime", LocalDateTime.now().toString());
        assertEquals("Emin", student.getName());
        testReporter.publishEntry("endTime", LocalDateTime.now().toString());
    }
}
