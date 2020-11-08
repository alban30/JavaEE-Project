package com.pereiraalban.web.jdbc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
    	
    		Cookie [] cookies = request.getCookies();
    		if(cookies!= null){
    			for(Cookie cookie:cookies){
    				if(cookie.getName().equals("uname"))
    					request.setAttribute("uname", cookie.getValue()) ;
    				}
    			}
    	
    	
    		request.getRequestDispatcher("login-student.jsp").forward(request, response);
    		}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password= request.getParameter("password");
				
		try {
			if (userDbUtil.loginCheck(username, password)) {
				
				Cookie cookie = new Cookie("uname", username);
				cookie.setMaxAge(60*60*24) ; // in seconds, here for 24 hours
				response.addCookie(cookie) ;
				HttpSession session = request.getSession();
				session.setAttribute("uname", username);
				request.getRequestDispatcher("/list-todos.jsp").forward(request, response);
				
			}
			else {
				response.sendRedirect("error.jsp");
			}
			
				
		}catch (ClassNotFoundException e) {
				
			e.printStackTrace();
		}
	}

}
