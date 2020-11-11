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
    		request.getRequestDispatcher("login.jsp").forward(request, response);
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
				
		try {
			if (userDbUtil.loginCheck(username, password))  { //if the password entered matches the database
				String profession = userDbUtil.fetchProfession(username); // getting the profession from the user 
				HttpSession session = request.getSession(); 
				session.setAttribute("uname", username); //storing username as a session variable
				session.setAttribute("prof", profession); //storing profession as a session variable
				response.sendRedirect("TodoControllerServlet"); //redirecting to the list page
			}
			else {
				response.sendRedirect("error-login.jsp"); //in case the password is incorrect
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
