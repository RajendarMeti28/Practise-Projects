package com.rajendar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rajendar.dto.Student;
import com.rajendar.util.jdbcUtil;

public class StudentDaoImpl implements IStudentDao {
	
	private Connection con;
	private PreparedStatement stmt;

	@Override
	public String saveStudent(Student student) {
		String status = "";
		String insertQuery = "INSERT INTO STUDENT(`name`,`email`,`city`) values (?,?,?)";
		int noOfRows = 0;
		try {
			con = jdbcUtil.getJdbcConnection();
			if(con!=null) {
				stmt = con.prepareStatement(insertQuery);
			}
			if(stmt!=null) {
				stmt.setString(1, student.getName());
				stmt.setString(2, student.getEmail());
				stmt.setString(3, student.getCity());
				noOfRows = stmt.executeUpdate();
			}
			if(noOfRows==1) {
				status="success";
			}else {
				status = "failure";
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return status;
	}

	@Override
	public String updateStudent(Student student) {
		String updateQuery = "UPDATE STUDENT SET name=?, email=?, city=? WHERE sid=?";
		String status="";
		int noOfRows=0;
		try {
			con = jdbcUtil.getJdbcConnection();
			if(con!=null) {
				stmt = con.prepareStatement(updateQuery);
			}
			if(stmt!=null) {
				stmt.setString(1, student.getName());
				stmt.setString(2, student.getEmail());
				stmt.setString(3, student.getCity());
				stmt.setInt(4, student.getsId());
				noOfRows = stmt.executeUpdate();
			}
			if(noOfRows==1) {
				status="success";
			}else {
				status = "failure";
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return status;
	}

	@Override
	public String deleteStudentById(int id) {
		String deleteQuery = "DELETE FROM STUDENT WHERE sid=?";
		String status = "";
		int noOfRows = 0;
		try {
			con = jdbcUtil.getJdbcConnection();
			if(con!=null) {
				stmt = con.prepareStatement(deleteQuery);
			}
			if(stmt!=null) {
				stmt.setInt(1, id);
				noOfRows = stmt.executeUpdate();
			}
			if(noOfRows==1) {
				status="success";
			}else {
				status = "failure";
			}
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return status;
	}

	@Override
	public Student getStudentById(int id) {
		String selectQuery = "SELECT sid,name,email,city FROM STUDENT where sid = ?";
		Student student = null;
		ResultSet rs = null;
		try {
			con = jdbcUtil.getJdbcConnection();
			if(con!=null) {
				stmt = con.prepareStatement(selectQuery);
			}
			if(stmt!=null) {
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
			}
			if(rs.next()) {
				student = new Student();
				student.setsId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setEmail(rs.getString(3));
				student.setCity(rs.getString(4));
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return student;
	}

}
