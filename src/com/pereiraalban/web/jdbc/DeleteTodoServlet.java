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
@WebServlet("/DeleteTodoServlet")
public class DeleteTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDBUtil todoDbUtil;
	@Resource(name="jdbc/todo")
	private DataSource dataSource;
	int id;
	
	@Override
	public void init() throws ServletException {
		super.init();
		todoDbUtil = new TodoDBUtil(dataSource);
	}
	
	public DeleteTodoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("prof").equals("instructor")) { // if the profession fetched is an instuctor
			id = Integer.parseInt(request.getParameter("todoId"));  // retriving the id parameter
			todoDbUtil.deleteTodo(id);// removing the description 
			response.sendRedirect("TodoControllerServlet"); // refreching the url
		}
		else {
			request.getRequestDispatcher("error.jsp").forward(request, response); // case if we are not a instructor
		}
	}
}

