package com.victor.web.jdbc;

import java.io.IOException;


import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private StudentDBUtil studentDbUtil;
	@Resource(name="jdbc/studentdb")
	
	
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		studentDbUtil = new StudentDBUtil(dataSource);
	}
	public AddStudentServlet() {
		 super();
		 // TODO Auto-generated constructor stub
		 }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/add-student.jsp");
		dispatcher.forward(request, response);
		
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fn= request.getParameter("firstName");
		String ln= request.getParameter("lastName");
		String email = request.getParameter("email");
		Student student = new Student(fn,ln,email);
		try {
			studentDbUtil.addStudent(student);
		}catch (Exception e) {
				
			e.printStackTrace();
		}
		response.sendRedirect("StudentControllerServlet");
	}
	
	

}
