package com.muditasoft.part01.service;


import com.muditasoft.part01.model.Course;
import com.muditasoft.part01.model.Lecturer;
import com.muditasoft.part01.model.Semester;

import java.util.Optional;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public interface LecturerService {
	Optional<Lecturer> findLecturer(Course course, Semester semester);
}
