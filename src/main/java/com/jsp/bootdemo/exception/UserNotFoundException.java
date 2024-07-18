package com.jsp.bootdemo.exception;

public class UserNotFoundException extends RuntimeException{
	String message;

	public UserNotFoundException(String message) {
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}
}
