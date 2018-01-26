package com.n26.assignment.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends AbstractResourceException {
	private static final long serialVersionUID = 1L;

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	public String getUserMessage() {
		return "Error, the request format is invalid!";
	}
}
