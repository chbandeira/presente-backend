package com.presente.backend.exceptions;

public class StandardValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public StandardValidationException(String message) {
		super(message);
	}

}
