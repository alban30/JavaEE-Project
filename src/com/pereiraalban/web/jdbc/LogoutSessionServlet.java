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
@WebServlet("/LogoutSessionServlet")
public class LogoutSessionServlet extends HttpServlet {
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
    public LogoutSessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	HttpSession session = request.getSession(true);
    	session.invalidate();
		response.sendRedirect("LoginServlet");
    }
}
