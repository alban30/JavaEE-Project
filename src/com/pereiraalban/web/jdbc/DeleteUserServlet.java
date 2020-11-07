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
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDBUtil userDbUtil;
	@Resource(name="jdbc/todo")
	private DataSource dataSource;
	int id;
	
	@Override
	public void init() throws ServletException {
		super.init();
		userDbUtil = new UserDBUtil(dataSource);
	}
	
	public DeleteUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("userId"));
		userDbUtil.deleteStudent(id);
		response.sendRedirect("UserControllerServlet");
	}
}

