package com.muditasoft.part01.service.impl;


import com.muditasoft.part01.model.Course;
import com.muditasoft.part01.model.Lecturer;
import com.muditasoft.part01.model.LecturerRepository;
import com.muditasoft.part01.model.Semester;
import com.muditasoft.part01.service.LecturerService;

import java.util.Optional;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public class LecturerServiceImpl implements LecturerService {

	private final LecturerRepository lecturerRepository;

	public LecturerServiceImpl(LecturerRepository lecturerRepository) {
		this.lecturerRepository = lecturerRepository;
	}

	@Override
	public Optional<Lecturer> findLecturer(Course course, Semester semester) {
		if (course == null || semester == null) {
			throw new IllegalArgumentException("Can't find lecturer without course and semester");
		}
		return lecturerRepository.findByCourseAndSemester(course, semester);
	}
}
