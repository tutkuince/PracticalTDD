package com.muditasoft.part05;

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
}
