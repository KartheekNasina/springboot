package com.jsp.bootdemo.controller;

import java.rmi.StubNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.bootdemo.dto.Student;
import com.jsp.bootdemo.exception.StudentNotFoundException;
import com.jsp.bootdemo.helper.ResponseStructure;
import com.jsp.bootdemo.repository.StudentRepository;

@RestController //@controller +@responsebody(java object to json)
public class HomeController {
	@Autowired
	StudentRepository studentRepository;
	@PostMapping("/hi")
	public String m1() {
		return "Hello World";
	}
	@PostMapping("/save")
	public ResponseStructure<Student> saveStudent(@RequestBody Student student) {
		studentRepository.save(student);
		ResponseStructure<Student> responseStructure=new ResponseStructure<Student>();
		responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
		responseStructure.setMessage("Data Saved Successfully");
		responseStructure.setData(student);
		return responseStructure;
	}
	@GetMapping("/fetchstudent")
	public ResponseStructure<Student> fetchStudent(@RequestParam("id") int id) {
		Optional<Student> optional = studentRepository.findById(id);
		if(optional.isPresent()) {
		Student student = optional.get();
		ResponseStructure<Student> responseStructure=new ResponseStructure<Student>();
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Data Found Successfully");
		responseStructure.setData(student);
		return responseStructure;
		}else throw new StudentNotFoundException("Data Not Found");
	}
	@GetMapping("/fetchstudentbyname")
	public List<Student> fetchStudentByName(@RequestParam("name") String name){
		return studentRepository.findByName(name);
		
	}
	@GetMapping("/fetchstudentbyagegreater")
	public List<Student> fetchStudentByAgeGreater(@RequestParam("age") int age){
		return studentRepository.findByAgeGreaterThan(age);
	}
	@GetMapping("/fetchstudentbyagebetween")
	public List<Student> fetchStudentByAgeBetween(@RequestParam("start") int start,@RequestParam("end") int end){
		return studentRepository.findByAgeBetween(start, end);
	}
	@GetMapping("/fetchstudentbynameandmobilenumber")
	public Student fetchStudentByNameAndMobileNumber(@RequestParam("name") String name,@RequestParam("mobilenumber") long mobilenumber){
		return studentRepository.findByNameAndMobilenumber(name, mobilenumber);
	}
}
