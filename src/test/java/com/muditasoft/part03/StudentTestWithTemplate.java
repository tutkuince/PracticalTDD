package com.muditasoft.part03;

import com.muditasoft.part01.model.LecturerCourseRecord;
import com.muditasoft.part02.Student;
import com.muditasoft.part02.TestLifecycleReporter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentTestWithTemplate implements TestLifecycleReporter {
    private Student student;


    @BeforeAll
    void setUp() {  // no need to be static
        student = new Student("id1", "Tutku", "Ince");
    }

    @ExtendWith(RepeatedStudentTestTemplateInvocationContextProvider.class)
    @TestTemplate
    void addCourseToStudent(LecturerCourseRecord lecturerCourseRecord, int numberOfInvocation) {
        student.addCourse(lecturerCourseRecord);
        assertEquals(numberOfInvocation, student.getStudentCourseRecords().size());
    }
}
