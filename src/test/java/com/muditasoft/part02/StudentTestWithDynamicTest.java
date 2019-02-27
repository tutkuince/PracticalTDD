package com.muditasoft.part02;

import com.muditasoft.part01.model.Course;
import com.muditasoft.part01.model.LecturerCourseRecord;
import com.muditasoft.part01.model.Semester;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.ThrowingConsumer;

import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.junit.jupiter.api.DynamicTest.stream;

public class StudentTestWithDynamicTest {

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("1", "Tutku", "Ince");
    }

    @TestFactory
    Stream<DynamicNode> addCourseToStudentWithCourseCodeAndCourseType() {


        return Stream.of("101", "103", "105")
                .map(courseCode -> dynamicContainer("Add course<" + courseCode + "> to student",
                        Stream.of(Course.CourseType.MANDATORY, Course.CourseType.ELECTIVE)
                                .map(courseType -> dynamicTest("Add course<" + courseType + "> to student", () -> {
                                    final Course course = Course.newCourse().withCode(courseCode).withCourseType(courseType).course();
                                    final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
                                    student.addCourse(lecturerCourseRecord);
                                    assertTrue(student.isTakeCourse(course));
                                })))
                );
    }

    @TestFactory
    Stream<DynamicTest> addCourseToStudentWithCourseCode() throws Exception {

        final Iterator<String> courseCodeGenerator = Stream.of("101", "103", "105").iterator();
        Function<String, String> displayNameGenerator = courseCode -> "Add course<" + courseCode + "> to student";
        ThrowingConsumer<String> testExecutor = courseCode -> {

            final Course course = Course.newCourse().withCode(courseCode).withCourseType(Course.CourseType.MANDATORY).course();
            final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertTrue(student.isTakeCourse(course));
        };

        return stream(courseCodeGenerator, displayNameGenerator, testExecutor);
    }
}
