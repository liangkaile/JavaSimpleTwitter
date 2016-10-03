package com.kaile.rest.exception;

public class ErrorData {
	private long errorId;
	private String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public long getErrorId() {
		return errorId;
	}
	public void setErrorId(long errorId) {
		this.errorId = errorId;
	}
	
	public ErrorData(long errorId, String errorMessage) {
		this.errorId = errorId;
		this.errorMessage = errorMessage;
	}
	
}
