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

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

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
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("prof").equals("instructor")) { // if the profession fetched is an instuctor
			id = Integer.parseInt(request.getParameter("todoId")); // retriving the id parameter
			Todo todo = todoDbUtil.fetchTodo(id); // fetching the description with the given id from the database
			request.setAttribute("Todo", todo);
			request.getRequestDispatcher("edit-todo.jsp").forward(request, response); // redirectig to the edit functionality
		}
		else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		String description = request.getParameter("description");// retriving the edited description
		Todo todo = new Todo(id,description);// creating a new Todo object with the modified description
		todoDbUtil.updateTodo(todo); // update the description
		response.sendRedirect("TodoControllerServlet");//redirecting the request to the previous servlet
	}
}

