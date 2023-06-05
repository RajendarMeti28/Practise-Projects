package com.rajendar.service;

import com.rajendar.dto.Student;

public interface IStudentService {
	
	public String saveStudent(Student student);
	public String updateStudent(Student student);
	public String deleteStudentById(int id);
	public Student getStudentById(int id);

}
