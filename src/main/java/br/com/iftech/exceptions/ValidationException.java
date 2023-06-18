package br.com.iftech.exceptions;

import org.springframework.validation.FieldError;

public class ValidationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	private FieldError fieldError;
	public ValidationException(String msg,FieldError fieldError) {
		super(msg);
		this.fieldError = fieldError;
	}
	
	public FieldError getFieldError() {
		return fieldError;
	}

}
