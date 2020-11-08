<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Web login</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<div id="wrapper">
	<div id="header">
		<h2>Welcome to Todo List Login</h2>
	</div>
</div>

<div id="container">
	<h3>Enter unsername and password</h3>
		
	<form action="LoginServlet" method="post">
			
			<table>
				<tbody>
					<tr>
						<td><label>Username:</label></td>
						<td><input type="text" name="username" value = "${User.username}"></td>
					</tr>

					<tr>
						<td><label>Password:</label></td>
						<td><input type="text" name="password" value = "${User.password}"></td>
					</tr>
					
					<tr>
						<td><input type="submit" value="login" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>		
		
	</div>
</body>
</html>