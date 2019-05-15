<%@ page import="java.util.*, com.erkan.web.jdbc.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head> 

<link type= "text/css" rel="stylesheet" href="css/style.css">

</head>

<% 

	List<Student> theStudents = (List<Student>) request.getAttribute("STUDENT_LIST");

%>


<body>


	<input type= "button" value="Add Student" 
		   onclick="window.location.href='add-student-form.jsp';"
		   class="add-student-button"
	/> 
	

	
	<table>
	

	
	<tr>
		<th> First Name</th>
		<th> Last Name</th>
		<th> Email</th>
		<th> Action </th>
	</tr>

	<c:forEach var="tempStudent" items="${STUDENT_LIST}">
	
	<c:url var="tempLink" value="StudentControllerServlet">
		<c:param name="command" value="LOAD" />
		<c:param name="studentId" value="${tempStudent.id}" />
	</c:url>
	
	<c:url var="deleteLink" value="StudentControllerServlet">
		<c:param name="command" value="DELETE" />
		<c:param name="studentId" value="${tempStudent.id}" />
	</c:url>
	
	<tr>
		<td> ${tempStudent.firstName } </td>
		<td> ${tempStudent.lastName } </td>
		<td> ${tempStudent.email } </td>
		<td> <a href="${tempLink}">Update</a> 
		 	| 
		 	<a href="${deleteLink}" 
		 	onclick="if (!(confirm('Sure you want to delete this student?'))) return false">
		 	Delete</a>
		 </td>		
	</tr>
	
	</c:forEach>

	
	</table>

</body>
</html>

