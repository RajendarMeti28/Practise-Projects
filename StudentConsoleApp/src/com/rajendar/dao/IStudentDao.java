package com.rajendar.dao;

import com.rajendar.dto.Student;

public interface IStudentDao {

	
	public String saveStudent(Student student);
	public String updateStudent(Student student);
	public String deleteStudentById(int id);
	public Student getStudentById(int id);

}
