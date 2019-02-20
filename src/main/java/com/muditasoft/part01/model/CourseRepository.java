package com.muditasoft.part01.model;

import java.util.Optional;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public interface CourseRepository {

	Optional<Course> findByExample(Course course);

	Optional<Course> findByDepartmentAndCodeAndName(Department department, String code, String name);

}
