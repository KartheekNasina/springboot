package com.jsp.bootdemo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.bootdemo.dto.User;
import com.jsp.bootdemo.exception.UserNotFoundException;
import com.jsp.bootdemo.helper.Login;
import com.jsp.bootdemo.helper.ResponseStructure;
import com.jsp.bootdemo.repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;
	@PostMapping("/saveuser")
	public ResponseStructure<User> saveUser(@RequestBody User user){
		userRepository.save(user);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Data Saved Successfully");
		responseStructure.setData(user);
		return responseStructure;
	}
	@GetMapping("/loginvalidation")
	public ResponseStructure<User> loginUser(@RequestBody Login login){
		User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if(user!=null) {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatuscode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Data Found");
			responseStructure.setData(user);
			return responseStructure;
		}else throw new UserNotFoundException("Data Not Found");
	}
	@DeleteMapping("/deleteuser")
	public ResponseStructure<String> deleteUser(@RequestParam("id") int id){
		userRepository.deleteById(id);
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setMessage("Data Deleted");
		responseStructure.setStatuscode(HttpStatus.OK.value());
		return responseStructure;
	}
	@DeleteMapping("/deleteuserbyname")
	public ResponseStructure<String> deleteUserByName(@RequestParam("name") String name){
		userRepository.deleteByName(name);
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setMessage("Data Deleted");
		responseStructure.setStatuscode(HttpStatus.OK.value());
		return responseStructure;
	}
	@PutMapping("/update")
	public ResponseStructure<User> updateUser(@RequestBody User user){
		userRepository.save(user);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
		responseStructure.setMessage("Data updated Successfully");
		responseStructure.setData(user);
		return responseStructure;
	}
	@PatchMapping("/patchreq")
	public ResponseStructure<User> patchUser(@RequestParam("id")int id,@RequestParam("name")String name){
		Optional<User> optional = userRepository.findById(id);
		User user = optional.get();
		userRepository.save(user);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
		responseStructure.setMessage("Data updated Successfully");
		responseStructure.setData(user);
		return responseStructure;
	}
}
