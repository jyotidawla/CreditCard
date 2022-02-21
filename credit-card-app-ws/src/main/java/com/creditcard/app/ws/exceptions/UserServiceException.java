package com.creditcard.app.ws.exceptions;

public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = 8195694555377901966L;

	public UserServiceException(String message) {
		super(message);
	}
}
