package com.rajendar.controller;

import com.rajendar.dto.Student;

public interface IStudentController {
	
	public String saveStudent(Student student);
	public String updateStudent(Student student);
	public String deleteStudentById(int id);
	public Student getStudentById(int id);

}