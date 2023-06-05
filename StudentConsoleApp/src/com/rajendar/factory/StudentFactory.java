package com.rajendar.factory;

import com.rajendar.controller.IStudentController;
import com.rajendar.controller.StudentControllerImpl;
import com.rajendar.dao.IStudentDao;
import com.rajendar.dao.StudentDaoImpl;
import com.rajendar.service.IStudentService;
import com.rajendar.service.StudentServiceImpl;

public class StudentFactory {
	
	private  static IStudentController stdController = null;
	private static IStudentService stdService = null;
	private static IStudentDao stdDao = null;
	
	private StudentFactory() {
		
	}
	
	public static IStudentController getStudentController() {
		try {
			if(stdController==null) {
				stdController = new StudentControllerImpl();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stdController;
	}
	
	public static IStudentService getStudentService() {
		if(stdService==null) {
			stdService = new StudentServiceImpl();
		}
		return stdService;
	}
	
	public static IStudentDao getStudentDao() {
		if(stdDao==null) {
			stdDao = new StudentDaoImpl();
		}
		return stdDao;
	}
	

}
