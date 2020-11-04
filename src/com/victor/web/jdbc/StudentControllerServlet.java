package com.victor.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		
		try {
			listStudents(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private void listStudents(HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		
		List<Student> students = studentDbUtil.getStudents();
		request.setAttribute("STUDENT_LIST", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}
	
	
}