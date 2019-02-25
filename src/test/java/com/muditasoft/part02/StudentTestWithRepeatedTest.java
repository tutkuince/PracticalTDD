package com.muditasoft.part02;

import com.muditasoft.part01.model.Course;
import com.muditasoft.part01.model.LecturerCourseRecord;
import com.muditasoft.part01.model.Semester;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentTestWithRepeatedTest implements TestLifecycleReporter {
    private Student student;

    @BeforeAll
    void setUp() {  // no need to be static
        student = new Student("id1", "Tutku", "Ince");
    }

    @DisplayName("Add Course to Student")
    @RepeatedTest(value = 5, name = "{displayName} => Add one course to student and student has {currentRepetition} courses")
    void addCourseToStudent(RepetitionInfo repetitionInfo) {
        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(String.valueOf(repetitionInfo.getCurrentRepetition())), new Semester());
        student.addCourse(lecturerCourseRecord);
        assertEquals(repetitionInfo.getCurrentRepetition(), student.getStudentCourseRecords().size());
    }
}
