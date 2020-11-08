package com.pereiraalban.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class UserDBUtil {
	
	private DataSource dataSource;
	
	public UserDBUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<User> getUsers() throws Exception {
		List<User> users = new ArrayList<User>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			String sql = "SELECT * FROM user ORDER BY username";
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next()){
				int id = myRs.getInt("id");
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String profession = myRs.getString("profession");
				
			User tempUser = new User(id, username, password, firstName, lastName, email, profession);
				users.add(tempUser);
			}
			
			return users;
				
		} finally {
			close(myConn, myStmt, myRs);
		}
		
	}
	
	public void addUser(User user) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
				
			// create sql for insert
			String sql = "INSERT INTO user" 
			+ "(username, password, first_name, last_name, email, profession)"
			+ "values(?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, user.getUsername());
			myStmt.setString(2, user.getPassword());
			myStmt.setString(3, user.getFirst_Name());
			myStmt.setString(4, user.getLast_Name());
			myStmt.setString(5, user.getEmail());
			myStmt.setString(6, user.getProfession());
			
			// execute sql insert
			myStmt.execute();
			
			} finally {
			// clean up JDBC objects
				close(myConn, myStmt, null);
		}
	}

	public User fetchUser(int id) {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		User user = null;
		
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			
			String sql = "SELECT * FROM user WHERE id=" + id;
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()) {
				String username = myRs.getString("username");
				String password = myRs.getString("password");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				String profession = myRs.getString("profession");
				
				user = new User(id, username, password, firstName, lastName, email, profession);
			}
			return user;
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
			
		} finally {
			close(myConn,myStmt,myRs);
		}
	}
	
	public void updateUser(User user) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "UPDATE user SET username=?, password=?, first_name=?, last_name=?, email=?, profession=? WHERE id=?";
			myStmt = myConn.prepareStatement(sql);
				
			myStmt.setString(1, user.getUsername());
			myStmt.setString(2, user.getPassword());
			myStmt.setString(3, user.getFirst_Name());
			myStmt.setString(4, user.getLast_Name());
			myStmt.setString(5, user.getEmail());
			myStmt.setString(6, user.getProfession());
			myStmt.setInt(7, user.getId());
			myStmt.execute();
			
		} catch(Exception e){
			System.out.println(e.getMessage());
		} finally{
			close(myConn, myStmt, null);
		}
	}
	
	public void deleteStudent(int id) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "DELETE FROM user WHERE id=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, id);
			myStmt.execute();
			
		} catch(Exception e){
			System.out.println(e.getMessage());
		} finally{
			close(myConn, myStmt, null);
		}
	}
	
public boolean loginCheck(String username, String password) throws ClassNotFoundException{
		
		Connection myConn=null;
		PreparedStatement myStmt = null;
		
		try {
			System.out.println(username);
			System.out.println(password);
		
			myConn = dataSource.getConnection();
			
			String sql= "select * from user where username = ? and password = ?";
			myStmt = myConn.prepareStatement(sql);			
			myStmt.setString(1, username);
			myStmt.setString(2, password);
			
			ResultSet rs = myStmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			
			
		} finally{
			close(myConn,myStmt,null);
		}
		return false;
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if(myStmt != null)
				myStmt.close();
			
			if(myRs != null)
				myRs.close();
			
			if(myConn != null)
				myConn.close();
			
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
