package com.kaile.rest.exception;

public enum AppError {
	//user Error
	USER_NOT_FOUND(1000, "User not found"),
	USERNAME_ALREADY_EXIST(1001, "Username already exist"),
	EMAIL_ALREADY_EXIST(1001, "Email already exist");
	
	
	//feed error
	
	
	private long errorId;
	private String errorMessage;
	
	public long getErrorId() {
		return errorId;
	}

	public void setErrorId(long errorId) {
		this.errorId = errorId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private AppError(long errorId, String errorMessage) {
		this.errorId = errorId;
		this.errorMessage = errorMessage;
	}

}
