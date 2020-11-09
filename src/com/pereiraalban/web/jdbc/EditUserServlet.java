package com.pereiraalban.web.jdbc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class EditStudentServlet
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
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
	
	public EditUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("prof").equals("instructor")) {
			User user = userDbUtil.fetchUser(id);
			id = Integer.parseInt(request.getParameter("userId"));
			request.setAttribute("User", user);
			request.getRequestDispatcher("edit-user.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String first_name = request.getParameter("firstName");
		String last_name = request.getParameter("lastName");
		String email = request.getParameter("email");
		String profession = request.getParameter("profession");
		User user = new User(id, username, password, first_name, last_name, email, profession);
		userDbUtil.updateUser(user);
		response.sendRedirect("UserControllerServlet");
	}
}

