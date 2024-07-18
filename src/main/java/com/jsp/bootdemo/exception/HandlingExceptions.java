package com.jsp.bootdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.bootdemo.dto.Student;
import com.jsp.bootdemo.helper.ResponseStructure;

@RestControllerAdvice
public class HandlingExceptions {
	@ExceptionHandler(value = StudentNotFoundException.class)
	public ResponseStructure<StudentNotFoundException> handle(StudentNotFoundException s){
		ResponseStructure<StudentNotFoundException>responseStructure=new ResponseStructure<StudentNotFoundException>();
		responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(s.getMessage());
		responseStructure.setData(s);
		return responseStructure;
	}
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseStructure<UserNotFoundException> handle(UserNotFoundException s){
		ResponseStructure<UserNotFoundException>responseStructure=new ResponseStructure<UserNotFoundException>();
		responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(s.getMessage());
		responseStructure.setData(s);
		return responseStructure;
	}
}
