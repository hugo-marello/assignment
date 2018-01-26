package com.n26.assignment.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractResourceException  extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public abstract HttpStatus getHttpStatus();
	public abstract String getUserMessage();
}
