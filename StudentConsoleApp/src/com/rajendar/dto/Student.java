package com.rajendar.dto;

public class Student {
	
	private int sId;
	private String name;
	private String email;
	private String city;
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Student [sId=" + sId + ", name=" + name + ", email=" + email + ", city=" + city + "]";
	}
	
	

}
