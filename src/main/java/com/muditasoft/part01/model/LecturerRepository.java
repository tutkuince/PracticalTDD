package com.muditasoft.part01.model;

import java.util.Optional;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public interface LecturerRepository {

	Optional<Lecturer> findByCourseAndSemester(Course course, Semester semester);

}
