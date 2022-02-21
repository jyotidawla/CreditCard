package com.creditcard.app.ws.ui.model.response;

public enum ErrorMessages {

	ALREADY_EXISTS("User already exists"),
	VALIDATION_ERROR("Validation Error"),
	INVALID_REQUEST("Invalid Request"),
	MISSING_REQUIRED_FIELD("Missing Required Field"),
	DATABASE_ERROR("Generic database error");

	private String errorMessage;

	ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
