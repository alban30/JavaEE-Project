package com.pereiraalban.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class TodoDBUtil {
	
	private DataSource dataSource;
	
	public TodoDBUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Todo> getTodos() throws Exception {
		List<Todo> todos = new ArrayList<Todo>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			String sql = "SELECT * FROM todolist";
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next()){
				int id = myRs.getInt("id");
				String description = myRs.getString("description");
				
			Todo tempTodo = new Todo(id, description);
			todos.add(tempTodo);
			}
			
			return todos;
				
		} finally {
			close(myConn, myStmt, myRs);
		}
		
	}
	
	public void addTodo(Todo todo) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
				
			// create sql for insert
			String sql = "INSERT INTO todolist (description) values(?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, todo.getDescription());
			
			// execute sql insert
			myStmt.execute();
			
			} finally {
			// clean up JDBC objects
				close(myConn, myStmt, null);
		}
	}

	public Todo fetchTodo(int id) {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		Todo todo = null;
		
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			
			String sql = "SELECT * FROM todolist WHERE id=" + id;
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()) {
				String description = myRs.getString("description");
				
				todo = new Todo(id, description);
			}
			return todo;
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
			
		} finally {
			close(myConn,myStmt,myRs);
		}
	}
	
	public void updateTodo(Todo todo) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "UPDATE todolist SET description=? WHERE id=?";
			myStmt = myConn.prepareStatement(sql);
				
			myStmt.setString(1, todo.getDescription());
			myStmt.setInt(2, todo.getId());
			myStmt.execute();
			
		} catch(Exception e){
			System.out.println(e.getMessage());
		} finally{
			close(myConn, myStmt, null);
		}
	}
	
	public void deleteTodo(int id) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "DELETE FROM todolist WHERE id=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, id);
			myStmt.execute();
			
		} catch(Exception e){
			System.out.println(e.getMessage());
		} finally{
			close(myConn, myStmt, null);
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
