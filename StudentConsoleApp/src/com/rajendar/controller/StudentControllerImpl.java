package com.rajendar.controller;

import com.rajendar.dto.Student;
import com.rajendar.factory.StudentFactory;
import com.rajendar.service.IStudentService;

public class StudentControllerImpl implements IStudentController {

	private IStudentService stdService;
	@Override
	public String saveStudent(Student student) {
		stdService = StudentFactory.getStudentService();
		return stdService.saveStudent(student);
	}

	@Override
	public String updateStudent(Student student) {
		stdService = StudentFactory.getStudentService();
		return stdService.updateStudent(student);
	}

	@Override
	public String deleteStudentById(int id) {
		stdService = StudentFactory.getStudentService();
		return stdService.deleteStudentById(id);
	}

	@Override
	public Student getStudentById(int id) {
		stdService = StudentFactory.getStudentService();
		return stdService.getStudentById(id);
	}

}
