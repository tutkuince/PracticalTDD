package com.muditasoft.part02;

import com.muditasoft.part01.model.Course;
import com.muditasoft.part01.model.LecturerCourseRecord;
import com.muditasoft.part01.model.Semester;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class StudentTestWithParameterizedMethods {

    private Student student;

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ValueSourceDemo {

        private int studentCourseSize = 0;

        @BeforeAll
        void setUp() {
            student = new Student("id1", "Tutku", "Ince");
        }


        // Parameterized tests make it possible to run a test multiple times with different arguments.
        // They are declared just like regular @Test methods but use the @ParameterizedTest annotation instead.
        // In addition, you must declare at least one source that will provide the arguments for each invocation
        // and then consume the arguments in the test method.
        @ParameterizedTest
        @ValueSource(strings = {"101", "103", "105"})
        void addCourseToStudent(String courseCode) {
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(courseCode), new Semester());
            student.addCourse(lecturerCourseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
            assertTrue(student.isTakeCourse(new Course(courseCode)));
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class EnumSourceDemo {

        @BeforeAll
        void setUp() {
            student = new Student("id1", "Tutku", "Ince");
        }

        @ParameterizedTest
        @EnumSource(Course.CourseType.class)
        void addCourseToStudent(Course.CourseType courseType) {
            final Course course = Course.newCourse()
                    .withCode(String.valueOf(new Random().nextInt(200)))
                    .withCourseType(courseType)
                    .course();

            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);

            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        @ParameterizedTest
        @EnumSource(value = Course.CourseType.class, names = "MANDATORY")
        void addMandatoryCoursesToStudent(Course.CourseType courseType) {
            final Course course = Course.newCourse()
                    .withCode(String.valueOf(new Random().nextInt(200)))
                    .withCourseType(courseType)
                    .course();

            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);

            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
            assertEquals(Course.CourseType.MANDATORY, course.getCourseType());
        }

        @ParameterizedTest
        @EnumSource(value = Course.CourseType.class, mode = EnumSource.Mode.EXCLUDE, names = "MANDATORY")
        void addElectiveCoursesToStudent(Course.CourseType courseType) {
            final Course course = Course.newCourse()
                    .withCode(String.valueOf(new Random().nextInt(200)))
                    .withCourseType(courseType)
                    .course();

            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);

            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
            assertEquals(Course.CourseType.ELECTIVE, course.getCourseType());
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class MethodSourceDemo {
        private int studentCourseSize = 0;

        @BeforeAll
        void setUp() {
            student = new Student("id1", "Tutku", "Ince");
        }


        // Parameterized tests make it possible to run a test multiple times with different arguments.
        // They are declared just like regular @Test methods but use the @ParameterizedTest annotation instead.
        // In addition, you must declare at least one source that will provide the arguments for each invocation
        // and then consume the arguments in the test method.
        @ParameterizedTest
        @MethodSource
        void addCourseToStudent(String courseCode) {
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(courseCode), new Semester());
            student.addCourse(lecturerCourseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
            assertTrue(student.isTakeCourse(new Course(courseCode)));
        }

        Stream<String> addCourseToStudent() {
            return Stream.of("101", "103", "105");
        }

        @ParameterizedTest
        @MethodSource("courseWithCodeAndType")
        void addCourseToStudent(String courseCode, Course.CourseType courseType) {
            final Course course = new Course(courseCode);
            course.setCourseType(courseType);
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
            assertTrue(student.isTakeCourse(new Course(courseCode)));

            assumingThat(courseCode.equals("101"), () -> assertEquals(Course.CourseType.MANDATORY, courseType));
            assumingThat(courseCode.equals("103"), () -> assertEquals(Course.CourseType.ELECTIVE, courseType));
            assumingThat(courseCode.equals("105"), () -> assertEquals(Course.CourseType.MANDATORY, courseType));
        }

        Stream<Arguments> courseWithCodeAndType() {
            return Stream.of(
                    Arguments.of("101", Course.CourseType.MANDATORY),
                    Arguments.of("103", Course.CourseType.ELECTIVE),
                    Arguments.of("105", Course.CourseType.MANDATORY)
            );
        }
    }
}
