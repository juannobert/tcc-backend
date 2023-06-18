package br.com.iftech.exceptions;

import org.springframework.validation.FieldError;

public class UserExistsException extends ValidationException{

	private static final long serialVersionUID = 1L;

	public UserExistsException(String msg,FieldError fieldError) {
		super(msg,fieldError);
	}

}
