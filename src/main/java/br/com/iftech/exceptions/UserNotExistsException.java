package br.com.iftech.exceptions;

public class UserNotExistsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UserNotExistsException(String msg) {
		super(msg);
	}

}
