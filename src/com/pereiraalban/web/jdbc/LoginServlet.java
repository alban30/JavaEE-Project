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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDBUtil userDbUtil;
	@Resource(name="jdbc/todo")
	private DataSource dataSource;
	
	
	public void init() {
        userDbUtil = new UserDBUtil(dataSource);
    }
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    		request.getRequestDispatcher("login-student.jsp").forward(request, response);
    		}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password= request.getParameter("password");
		
		try {
			System.out.println(username);
			System.out.println(password);
			if (userDbUtil.loginCheck(username, password)) {
				
				response.sendRedirect("todo-student.jsp");
			}
			else {
				response.sendRedirect("error.jsp");
			}
			
				
		}catch (ClassNotFoundException e) {
				
			e.printStackTrace();
		}
	}

}
