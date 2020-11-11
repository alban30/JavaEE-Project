package com.pereiraalban.web.jdbc;

import java.io.IOException;


import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/AddTodoServlet")
public class AddTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDBUtil todoDbUtil;
	@Resource(name="jdbc/todo")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		todoDbUtil = new TodoDBUtil(dataSource);
	}
	
	public AddTodoServlet() {
		 super();
		 // TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("prof").equals("instructor")) { //if the profession fetched is an instuctor
			request.getRequestDispatcher("add-todo.jsp").forward(request, response); // he can acces the addf functonality
		}
		else {
			request.getRequestDispatcher("error.jsp").forward(request, response); // case if we are not a instructor
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	// adding a description to the list
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		// TODO Auto-generated method stub
		String description = request.getParameter("description");
		Todo todo = new Todo(description);
		
		try {
			todoDbUtil.addTodo(todo);
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
		response.sendRedirect("TodoControllerServlet");
	}
}
