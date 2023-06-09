package com.rajendar.service;

import com.rajendar.dao.IStudentDao;
import com.rajendar.dto.Student;
import com.rajendar.factory.StudentFactory;

public class StudentServiceImpl implements IStudentService {

	private IStudentDao stdDao;
	@Override
	public String saveStudent(Student student) {
		stdDao = StudentFactory.getStudentDao();
		return stdDao.saveStudent(student);
	}

	@Override
	public String updateStudent(Student student) {
		stdDao = StudentFactory.getStudentDao();
		return stdDao.updateStudent(student);
	}

	@Override
	public String deleteStudentById(int id) {
		stdDao = StudentFactory.getStudentDao();
		return stdDao.deleteStudentById(id);
	}

	@Override
	public Student getStudentById(int id) {
		stdDao = StudentFactory.getStudentDao();
		return stdDao.getStudentById(id);
	}

}
