<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Web User Tracker</title>
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
				
				<input type="button" value="Add User" onclick="window.location.href= 'add-user.jsp' ; return false;" class="add-button"/>
				
				<table>
					<tr>
						<th>Username</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Profession</th>
					</tr>
					<c:forEach var="tempUser" items="${USER_LIST}" >
						<c:url var="EditLink" value="EditUserServlet">
							<c:param name="userId" value="${tempUser.id}"/>
						</c:url>
						<c:url var="DeleteLink" value="DeleteUserServlet">
							<c:param name="userId" value="${tempUser.id}"/>
						</c:url>
							<tr>
								<td>${tempUser.username}</td>
								<td>${tempUser.first_Name}</td>
								<td>${tempUser.last_Name}</td>
								<td>${tempUser.email}</td>
								<td>${tempUser.profession}</td>
								<td><a href="${EditLink}"> Edit</a></td>
								<td><a href="${DeleteLink}"> Delete</a></td>
							</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</body>
</html>
