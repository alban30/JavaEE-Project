package com.victor.web.jdbc;

public class Student {
	
	
	int id;
	String first_Name;
	String last_Name;
	String email;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_Name() {
		return first_Name;
	}
	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}
	public String getLast_Name() {
		return last_Name;
	}
	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Student(int id, String first_Name, String last_Name, String email) {	
		this.id = id;
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.email = email;
	}
	
	public Student(String first_Name, String last_Name, String email) {
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", First_Name=" + first_Name + ", Last_Name=" + last_Name + ", email=" + email
				+ "]";
	}
	
	
	

}
