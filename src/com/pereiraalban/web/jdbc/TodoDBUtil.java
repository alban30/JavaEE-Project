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
	
	// this function fetches the descriptions from the database and store them into a list
	public List<Todo> getTodos() throws Exception {
		List<Todo> todos = new ArrayList<Todo>(); // creating a new list
		Connection myConn = null; 
		Statement myStmt = null; 
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection(); // getting a connection from the pool
			myStmt = myConn.createStatement(); // creating a statement 	
			String sql = "SELECT * FROM todolist";
			myRs = myStmt.executeQuery(sql); // executing the statement and put the result in a ResultSet
			
			while(myRs.next()){ //exploring the result set
				int id = myRs.getInt("id");
				String description = myRs.getString("description");
				
			Todo tempTodo = new Todo(id, description); // creating a new Todo object for each row from the database
			todos.add(tempTodo);// adding the Todo object to the list
			}
			
			return todos; // retrun the list
				
		} finally {
			close(myConn, myStmt, myRs);
		}
		
	}
	
	//this function adds a new descrption to the Todo database
	public void addTodo(Todo todo) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
			myConn = dataSource.getConnection(); // get db connection			
			String sql = "INSERT INTO todolist (description) values(?)"; // create sql for insert			
			myStmt = myConn.prepareStatement(sql); 
			myStmt.setString(1, todo.getDescription()); //set the param values for the student
			myStmt.execute(); // execute sql insert
			
			} finally {
				close(myConn, myStmt, null); // clean up JDBC objects
		}
	}
	
	// this function retrieve a description from the database with a given id
	public Todo fetchTodo(int id) {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		Todo todo = null;
		
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.createStatement();
			
			String sql = "SELECT description FROM todolist WHERE id=" + id;
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
	// this function updates a description from the database with a given id
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
	
	// this function deletes a description from the database with a given id
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
