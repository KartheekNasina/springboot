package com.jsp.bootdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.bootdemo.dto.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	List<Student> findByName(String name);
	List<Student> findByAgeGreaterThan(int age);
	List<Student> findByAgeLessThan(int age);
	Student findByMobilenumber(long mobilenumber);
	List<Student> findByAgeBetween(int start,int end);
	Student findByNameAndMobilenumber(String name,long mobilenumber);
}
