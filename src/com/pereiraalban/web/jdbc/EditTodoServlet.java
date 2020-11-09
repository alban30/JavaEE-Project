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
@WebServlet("/EditTodoServlet")
public class EditTodoServlet extends HttpServlet {
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
	
	public EditTodoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("todoId"));
		Todo todo = todoDbUtil.fetchTodo(id);
		request.setAttribute("Todo", todo);
		request.getRequestDispatcher("edit-todo.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		String description = request.getParameter("description");
		Todo todo = new Todo(id, description);
		todoDbUtil.updateTodo(todo);
		response.sendRedirect("TodoControllerServlet");
	}
}

