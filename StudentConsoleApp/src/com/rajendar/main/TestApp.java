package com.rajendar.main;

import java.util.Scanner;

import com.rajendar.controller.IStudentController;
import com.rajendar.dto.Student;
import com.rajendar.factory.StudentFactory;

public class TestApp {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.println("1: CREATE");
			System.out.println("2: READ");
			System.out.println("3: UPDATE");
			System.out.println("4: DELETE");
			System.out.println("5: EXIT");
			System.out.println("Please choose one option.. [0,1,2,3,4,5]");
			int in = input.nextInt();
			IStudentController stdController = StudentFactory.getStudentController();
			Student student;
			int id;
			String status;
			switch(in) {	
			case 1:
				student = new Student();
				System.out.println("\nEnter name:");
				String name = input.next();
				student.setName(name);
				System.out.println("Enter email:");
				String email = input.next();
				student.setEmail(email);
				System.out.println("Enter city:");
				String city = input.next();
				student.setCity(city);
				status = stdController.saveStudent(student);
				if(status.equalsIgnoreCase("success")) {
					System.out.println("Record Inserted Succesfully..");
				}
				else {
					System.out.println("Insertion failed..");
				}
				break;
			case 2:
				System.out.println("Enter the id:");
				id = input.nextInt();
				student = stdController.getStudentById(id);
				if(student!=null) {
					System.out.println("Name of the student: "+student.getName());
					System.out.println("Email of the student: "+student.getEmail());
					System.out.println("City of the student: "+student.getCity());
					
				}else {
					System.out.println("No records found for given id to read: "+id);
				}
				break;
			case 3:
				System.out.println("Enter the id:");
				id = input.nextInt();
				student = stdController.getStudentById(id);
				
				if(student!=null) {
					Student newStudent = new Student();
					newStudent.setsId(student.getsId());
					System.out.println("\nEnter name: [Old Name: "+student.getName()+"]:");
					String name1 = input.next();
					if(name1!=null && name1!="") {
						newStudent.setName(name1);
					}else {
						newStudent.setName(student.getName());
					}
					System.out.println("Enter email: [Old Email: "+student.getEmail()+"]:");
					String email1 = input.next();
					if(email1!=null && email1!="") {
						newStudent.setEmail(email1);
					}else {
						newStudent.setEmail(student.getEmail());
					}
					System.out.println("Enter city: [Old City: "+student.getCity()+"]:");
					String city1 = input.next();
					if(city1!=null && city1!="") {
						newStudent.setCity(city1);
					}else {
						newStudent.setCity(student.getCity());
					}
					status = stdController.updateStudent(newStudent);
					if(status.equalsIgnoreCase("success")) {
						System.out.println("Record Updated Succesfully..");
					}
					else {
						System.out.println("Updation failed..");
					}
				}
				else {
					System.out.println("No records found for given id to update: "+id);
				}
				break;
			case 4:
				System.out.println("Enter the id:");
				id = input.nextInt();
				student = stdController.getStudentById(id);
				if(student!=null) {
					status = stdController.deleteStudentById(id);
					if(status.equalsIgnoreCase("success")) {
						System.out.println("Record Deleted Succesfully..");
					}
					else {
						System.out.println("Deletion failed..");
					}
					
				}else {
					System.out.println("No records found for given id to read: "+id);
				}
				break;
			case 5:
				System.out.println("Thanks for using our application..");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Input, Try again...");
			
			}
		}

	}

}
