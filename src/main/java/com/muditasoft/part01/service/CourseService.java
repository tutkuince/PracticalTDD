package com.muditasoft.part01.service;


import com.muditasoft.part01.model.Course;
import com.muditasoft.part01.model.Department;

import java.util.Optional;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public interface CourseService {
	Optional<Course> findCourse(Course course);

	Optional<Course> findCourse(Department department, String code, String name);
}
