<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
		<head>
			<meta charset="ISO-8859-1">
			<title>Todolist - Error 404</title>
		</head>
		
	<body>
		<div id="wrapper">
			<div id="header">
				<h2> ERROR 404 - Access denied</h2>
			</div>
		</div>
		<p>
			<c:url var="BackLink" value="TodoControllerServlet"></c:url>
			<a href="${BackLink}">Back to todolist</a>
		</p>
		<div style="clear:both;"></div>
	</body>
</html>