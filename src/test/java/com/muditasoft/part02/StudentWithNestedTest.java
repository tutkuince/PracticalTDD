package com.muditasoft.part02;

import org.junit.jupiter.api.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Tag("student")
public class StudentWithNestedTest {

    @Nested // For nested class
    @DisplayName("Create Student")
    class CreateStudent {

        @Test
        @DisplayName("Test every student must have an id, name and surname")
        @Tag("createStudent")
        void shouldCreateStudentWithIdNameAndSurname() {

            Student stdTutku = new Student("1", "Tutku", "Ince");

            assertEquals("Tutku", stdTutku.getName()); // "Tutku".equals(stdTutku.getName())
            assertEquals("Tutku", stdTutku.getName(), "Student's name");
            assertNotEquals("Utku", stdTutku.getName(), "Student's name");

            assertTrue(stdTutku.getName().startsWith("T"));
            assertTrue(stdTutku.getName().startsWith("T"), () -> "Student's name " + "starts with T");
            assertFalse(() -> {
                Student stdEmin = new Student("id1", "Emin", "Koklu");
                return stdEmin.getName().endsWith("U");
            }, () -> "Student's name ends with U");

            final Student stdUgur = new Student("2", "Ugur", "Batikan");

            assertArrayEquals(new String[]{"Tutku", "Ugur"},
                    Stream.of(stdTutku, stdUgur).map(Student::getName).toArray());

            Student student = stdTutku;

            assertSame(stdTutku, student); // stdTutku == student
            assertNotSame(stdUgur, student); // stdUgur != student

        }

        /**
         * grouped assertions failed grouped assertions dependent assertions
         */
        @Test
        @DisplayName("Test every student must have an id, name and surname with grouped assertions")
        @Tag("createStudent")
        void shouldCreateStudentWithIdNameAndSurnameWithGroupedAssertions() {

            // In a grouped assertions all assertions are executed,
            Student stdTutku = new Student("1", "Tutku", "Ince");

            assertAll("Student's name check",
                    () -> assertEquals("Tutku", stdTutku.getName()),
                    () -> assertEquals("Tutku", stdTutku.getName(), "Student's name"),
                    () -> assertNotEquals("Utku", stdTutku.getName(), "Student's name"));

            // and any failures will be reported together,
            assertAll("Student's name character check", () -> assertTrue(stdTutku.getName().startsWith("T")),
                    () -> assertTrue(stdTutku.getName().startsWith("T"), () -> "Student's name starts with T"),
                    () -> assertFalse(() -> {
                        Student stdEmin = new Student("id1", "Emin", "Koklu");
                        return stdEmin.getName().endsWith("U");
                    }, () -> "Student's name ends with U"));

            // dependent assertions
            assertAll(() -> {
                final Student stdUgur = new Student("2", "Ugur", "Batikan");

                assertArrayEquals(new String[]{"Tutku", "Ugur"},
                        Stream.of(stdTutku, stdUgur).map(Student::getName).toArray());

            }, () -> {
                Student student = stdTutku;
                final Student stdUgur = new Student("2", "Ugur", "Batikan");

                assertSame(stdTutku, student);
                assertNotSame(student, stdUgur);
            });
        }

        @Test
        @Disabled("No more valid scenario")
        @DisplayName("Test that student must have only number id")
        @Tag("createStudent")
        void shouldCreateStudentWithNumberId() {
            assertThrows(IllegalArgumentException.class, () -> new Student("id", "Tutku", "Ince"));
        }
    }
}
