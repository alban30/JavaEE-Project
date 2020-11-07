package com.pereiraalban.web.jdbc;

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
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDBUtil userDbUtil;
	@Resource(name="jdbc/todo")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		userDbUtil = new UserDBUtil(dataSource);
	}
	
	public AddUserServlet() {
		 super();
		 // TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/add-user.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String first_name = request.getParameter("firstName");
		String last_name = request.getParameter("lastName");
		String email = request.getParameter("email");
		String profession = request.getParameter("profession");
		User user = new User(username, first_name, last_name, email, profession);
		
		try {
			userDbUtil.addUser(user);
		} catch (Exception e) {
				
			e.printStackTrace();
		}
		
		response.sendRedirect("UserControllerServlet");
	}
}
