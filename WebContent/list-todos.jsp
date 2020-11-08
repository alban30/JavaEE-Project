<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Web Todo Tracker</title>
		<link type="text/css" rel="stylesheet" href="css/style.css">
	</head>
	
	<body>
		<!-- ${STUDENT_LIST}-->
		<div id="wrapper">
			<div id="header">
				<h2>ESILV Engineer School</h2>
			</div>
		</div>
		
		<div id="container">
			<div id="content">
				
				<input type="button" value="Add Todo" onclick="window.location.href= 'add-todo.jsp' ; return false;" class="add-button"/>
				
				<table>
					<tr>
						<th>Number</th>
						<th>Description</th>
					</tr>
					<c:forEach var="tempTodo" items="${TODO_LIST}" >
						<c:url var="EditLink" value="EditTodoServlet">
							<c:param name="todoId" value="${tempTodo.id}"/>
						</c:url>
						<c:url var="DeleteLink" value="DeleteTodoServlet">
							<c:param name="todoId" value="${tempTodo.id}"/>
						</c:url>
							<tr>
								<td>${tempTodo.id}</td>
								<td>${tempTodo.description}</td>
								<td><a href="${EditLink}"> Edit</a></td>
								<td><a href="${DeleteLink}"> Delete</a></td>
							</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</body>
</html>