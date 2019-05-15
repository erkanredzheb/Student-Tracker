
<!DOCTYPE html>
<html>

<head> 
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
<body>

<h3> Update a Student </h3>

</body>

	<form action="StudentControllerServlet" method="GET">

		<input type="hidden" name="command" value="UPDATE">
		
		<input type="hidden" name="studentId" value="${THE_STUDENT.id}">
		
		<label> First name: </label>
		<input type="text" name="firstName" value="${THE_STUDENT.firstName}"/>
	
		<label> Last name: </label>
		<input type="text" name="lastName" value="${THE_STUDENT.lastName}"/>
		
		<label> Email: </label>
		<input type="text" name="email" value="${THE_STUDENT.email}"/>
		
		<label></label>
		<input type="submit" value="Save" class="save"/>
	</form>

</head>

</html>