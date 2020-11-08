package com.pereiraalban.web.jdbc;

public class User {
	int id;
	String username;
	String password;
	String first_Name;
	String last_Name;
	String email;
	String profession;
	
	public User(int id, String username, String password, String first_Name, String last_Name, String email, String profession) {	
		this.id = id;
		this.username = username;
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.email = email;
		this.profession = profession;
	}

	public User(String username, String password, String first_Name, String last_Name, String email, String profession) {
		this.username = username;
		this.password = password;
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.email = email;
		this.profession = profession;
	}
	
	@Override
	public String toString() {
		return "Student [id = " + this.id + ", username = " + this.username + ", First_name = " + this.first_Name + 
				", Last_name = " + this.last_Name + ", email = " + this.email + ", profession = " + this.profession + "]";
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
}
