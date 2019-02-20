package com.muditasoft.part01.service;

import com.muditasoft.part01.model.Course;
import com.muditasoft.part01.model.Semester;
import com.muditasoft.part01.model.Student;
import com.muditasoft.part01.model.StudentCourseRecord;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public interface StudentService {
	void addCourse(String studentId, Course course);

	void addCourse(String studentId, Course course, Semester semester);

	void dropCourse(String studentId, Course course);

	void addGrade(String studentId, Course course, StudentCourseRecord.Grade grade);

	boolean isTakeCourse(String studentId, Course course);

	BigDecimal gpa(String studentId);

	List<TranscriptItem> transcript(String studentId);

	Optional<Student> findStudent(String studentId);

	void deleteStudent(String studentId);
}
