<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
		<title>Todolist - Add todo</title>
	
		<link type="text/css" rel="stylesheet" href="css/style.css">
		<link type="text/css" rel="stylesheet" href="css/add-style.css">	
	</head>
	
	<body>
		<c:url var="LogoutLink" value="LogoutSessionServlet"></c:url>
		<a href="${LogoutLink}"> Logout</a>
		
		<div id="wrapper">
			<div id="header">
				<h2>ESILV Engineer School</h2>
			</div>
		</div>
		
		<div id="container">
			<h3>Add Todo</h3>
			
			<form action="AddTodoServlet" method="post">
				
				<table>
					<tbody>
						<tr>
							<td><label>Description:</label></td>
							<td><input type="text" name="description" value="${Todo.description}" /></td>
						</tr>
						<tr>
							<td><input type="submit" value="Save" /></td>
						</tr>
						
					</tbody>
				</table>
			</form>
			
			<div style="clear: both;"></div>
			
			<p>
				<a href="TodoControllerServlet">Back to List</a>
			</p>
		</div>
	</body>
</html>