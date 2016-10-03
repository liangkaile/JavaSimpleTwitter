package com.kaile.rest.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author kaliang
 *
 */
public class AppException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8820668581095833553L;
	
	private int status;
	private List<ErrorData> errors;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<ErrorData> getErrors() {
		return errors;
	}
	public void setErrors(List<ErrorData> errors) {
		this.errors = errors;
	}
	
	public AppException(int status, AppError appError) {
		this.status = status;
		ErrorData errorData = new ErrorData(appError.getErrorId(), appError.getErrorMessage());
		List<ErrorData> errors = new ArrayList<ErrorData>();
		errors.add(errorData);
		this.errors = errors;
	}
	
	public AppException(int status, List<AppError> appErrorList) {
		this.status = status;
		
		this.errors = new ArrayList<ErrorData>();
		for(AppError appError : appErrorList) {
			ErrorData errorData = new ErrorData(appError.getErrorId(), appError.getErrorMessage());
			errors.add(errorData);
		}
	}

}
