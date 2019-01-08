package com.presente.backend.exceptions;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Integer status;
	private String field;

	public ValidationException(Integer status, String field, String message) {
		super(message);
		this.status = status;
		this.field = field;
	}

	public ValidationException(String field, String message) {
		super(message);
		this.field = field;
	}

	public Integer getStatus() {
		return status;
	}

	public String getField() {
		return field;
	}

}
