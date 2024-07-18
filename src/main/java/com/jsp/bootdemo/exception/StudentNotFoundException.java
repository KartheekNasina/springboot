package com.jsp.bootdemo.exception;

public class StudentNotFoundException extends RuntimeException{
	String message;

	public StudentNotFoundException(String message) {
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}
}
