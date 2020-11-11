package com.pereiraalban.web.jdbc;



public class Todo {
	//defing two private attriburte
	int id;
	String description;
	
	public Todo(int id, String description) {	
		this.id = id;
		this.description = description;
	}
	
	public Todo(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Todo [id = " + this.id + ", description = " + this.description + "]";
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
