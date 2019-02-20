package com.muditasoft.part01.model;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public class NoCourseFoundForStudentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoCourseFoundForStudentException(String message) {
		super(message);
	}

}
