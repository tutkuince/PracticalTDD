package com.muditasoft.part01.model;

/**
 * @author Tutku Ince
 *
 * @since Oct 8, 2018
 */
public class NotActiveSemesterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotActiveSemesterException(String message) {
		super(message);
	}
}
