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
	
	public boolean loginCheck(String username, String password) throws ClassNotFoundException{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql= "select * from user where username = ? and password = ?";
			myStmt = myConn.prepareStatement(sql);			
			myStmt.setString(1, username);
			myStmt.setString(2, password);
			
			ResultSet rs = myStmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		
		} catch(Exception e){
			System.out.println(e.getMessage());
			
			
		} finally{
			close(myConn, myStmt, null);
		}
		return false;
	}
	
	public String fetchProfession(String username) {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		String profession = null;
		
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			
			String sql = "SELECT profession FROM user WHERE username='" + username + "'";
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next()) {
				profession = myRs.getString("profession");		
			}
			
			return profession;
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
			
		} finally {
			close(myConn, myStmt, myRs);
		}
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
