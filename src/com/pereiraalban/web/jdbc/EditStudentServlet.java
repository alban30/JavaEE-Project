package com.pereiraalban.web.jdbc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class EditStudentServlet
 */
@WebServlet("/EditStudentServlet")
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDBUtil studentDbUtil;
	@Resource(name="jdbc/student")
	private DataSource dataSource;
	int id;
	
	@Override
	public void init() throws ServletException {
		super.init();
		studentDbUtil = new StudentDBUtil(dataSource);
	}
	
	public EditStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("studentId"));
		Student student = studentDbUtil.fetchStudent(id);
		request.setAttribute("Student", student);
		request.getRequestDispatcher("edit-student.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		String fn = request.getParameter("firstName");
		String ln = request.getParameter("lastName");
		String email = request.getParameter("email");
		Student student = new Student(id,fn,ln,email);
		studentDbUtil.updateStudent(student);
		response.sendRedirect("StudentControllerServlet");
	}
}

