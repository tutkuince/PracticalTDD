package com.muditasoft.part05;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTestWithAssertJAssertions {

    @Test
    void createStudent() {
        final Student student = new Student("id1", "Tutku", "Ince");

        assertThat(student.getName()).as("Student's name %s", student.getName())
                .doesNotContainOnlyWhitespaces()
                .isNotEmpty()  // not null, not empty but it can contains white spaces
                .isNotBlank()  //  doesNotContainOnlyWhitespaces + isNotEmpty
                .isEqualTo("Tutku")
                .isEqualToIgnoringCase("tutku")
                .isIn("Tutku", "Emin", "Ali")
                .isNotIn("Ali", "Veli")
                .startsWith("T")
                .doesNotStartWith("U")
                .endsWith("u")
                .doesNotEndWith("t")
                .contains("tku")
                .contains(List.of("u", "t", "k"))
                .hasSize(5)
                .matches("^T\\w{3}u$");

    }

    @Test
    void addCourseToStudent() {
        final Student stdTutku = new Student("id1", "Tutku", "Ince");
        final Student stdEmin = new Student("id2", "Emin", "Koklu");
        final Student stdUgur = new Student("id3", "Ugur", "Batikan");
        final Student stdAlper = new Student("id4", "Alper", "Omeroglu");
        final Student stdAral = new Student("id5", "Aral", "Ozbilgi");

        final List<Student> students = List.of(stdTutku, stdEmin, stdUgur, stdAlper, stdAral);

        assertThat(students).as("Student's List")
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .contains(stdTutku, stdEmin)
                .containsOnly(stdEmin, stdTutku, stdUgur, stdAlper, stdAral)   // full list must be in containsOnly, order not important
                .containsExactly(stdTutku, stdEmin, stdUgur, stdAlper, stdAral) // order is important
                .containsExactlyInAnyOrder(stdEmin, stdTutku, stdUgur, stdAlper, stdAral);  // same as containsOnly

        assertThat(students).as("Student's List with Student's name starts with 'A'")
                .filteredOn(student -> student.getName().startsWith("A")).hasSize(2)
                .containsOnly(stdAlper, stdAral);

        assertThat(students).as("Student's List with Student's name starts with 'A'")
                .filteredOn(new Condition<>() {
                    @Override
                    public boolean matches(Student value) {
                        return value.getName().startsWith("A");
                    }
                })
                .hasSize(2)
                .containsOnly(stdAlper, stdAral);
    }
}
