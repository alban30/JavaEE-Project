<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
		<title>Add User</title>
	
		<link type="text/css" rel="stylesheet" href="css/style.css">
		<link type="text/css" rel="stylesheet" href="css/add-style.css">	
	</head>
	
	<body>
		<div id="wrapper">
			<div id="header">
				<h2>ESILV Engineer School</h2>
			</div>
		</div>
		
		<div id="container">
			<h3>Add User</h3>
			
			<form action="AddUserServlet" method="post">
				
				<table>
					<tbody>
						<tr>
							<td><label>Username:</label></td>
							<td><input type="text" name="username" value="${User.username}" /></td>
						</tr>
						<tr>
							<td><label>Password:</label></td>
							<td><input type="text" name="username" value="${User.password}" /></td>
						</tr>
						<tr>
							<td><label>First name:</label></td>
							<td><input type="text" name="firstName" value="${User.first_Name}" /></td>
						</tr>
						<tr>
							<td><label>Last name:</label></td>
							<td><input type="text" name="lastName" value="${User.last_Name}" /></td>
						</tr>
						<tr>
							<td><label>Email:</label></td>
							<td><input type="text" name="email" value="${User.email}" /></td>
						</tr>
						<tr>
							<td><label>Profession:</label></td>
							<td><input type="text" name="profession" value="${User.profession}" /></td>
						</tr>
						<tr>
							<td><input type="submit" value="Save" /></td>
						</tr>
						
					</tbody>
				</table>
			</form>
			
			<div style="clear: both;"></div>
			
			<p>
				<a href="UserControllerServlet">Back to List</a>
			</p>
		</div>
	</body>
</html>