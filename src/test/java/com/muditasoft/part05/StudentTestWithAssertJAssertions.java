package com.muditasoft.part05;

import com.muditasoft.part01.model.Course;
import com.muditasoft.part01.model.Department;
import com.muditasoft.part01.model.LecturerCourseRecord;
import com.muditasoft.part01.model.Semester;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

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

        assertThat(students)
                .extracting(Student::getName) // like stream.map
                .filteredOn(name -> name.contains("u"))
                .hasSize(2)
                .containsOnly("Tutku", "Ugur");


        assertThat(students)
                .filteredOn(student -> student.getName().contains("u"))
                .extracting(Student::getName, Student::getSurname)  // used tuple for mapping more than 1 props
                .containsOnly(
                        tuple("Tutku", "Ince"),
                        tuple("Ugur", "Batikan")
                );


        final LecturerCourseRecord lecturerCourseRecord101 = new LecturerCourseRecord(new Course("101"), new Semester());
        final LecturerCourseRecord lecturerCourseRecord103 = new LecturerCourseRecord(new Course("103"), new Semester());
        final LecturerCourseRecord lecturerCourseRecord105 = new LecturerCourseRecord(new Course("105"), new Semester());

        stdTutku.addCourse(lecturerCourseRecord101);
        stdTutku.addCourse(lecturerCourseRecord103);
        stdTutku.addCourse(lecturerCourseRecord105);

        stdEmin.addCourse(lecturerCourseRecord101);
        stdEmin.addCourse(lecturerCourseRecord105);


        assertThat(students)
                .filteredOn(student -> student.getName().equals("Tutku") || student.getName().equals("Emin"))
                .flatExtracting(Student::getStudentCourseRecords)
                .hasSize(5)
                .filteredOn(studentCourseRecord -> studentCourseRecord.getLecturerCourseRecord().getCourse().getCode().equals("101"))
                .hasSize(2);

    }

    @Test
    void anotherCreateStudentTest() {
        Department department = new Department();
        final Student stdTutku = new Student("id1", "Tutku", "Ince", LocalDate.of(1989, 1, 15));
        stdTutku.setDepartment(department);
        final Student stdUtku = new Student("id1", "Utku", "Ince", LocalDate.of(1998, 3, 20));
        stdUtku.setDepartment(department);

        assertThat(stdTutku).as("Check student tutku info")
                .isNotNull()
                .hasSameClassAs(stdUtku)
                .isExactlyInstanceOf(Student.class) // must construct with Student
                .isInstanceOf(Object.class)    // can be super class
                .isNotEqualTo(stdUtku)
                .isEqualToComparingOnlyGivenFields(stdUtku, "surname")
                .isEqualToIgnoringGivenFields(stdUtku, "id", "name", "birthDate")
                .matches(student -> student.getSurname().equals(stdUtku.getSurname()))
                .hasFieldOrProperty("name")    // contains field
                .hasNoNullFieldsOrProperties()
                .extracting(Student::getName, Student::getSurname)
                .containsOnly("Tutku", "Ince");

    }
}
