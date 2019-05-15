
<!DOCTYPE html>
<html>

<head> 
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
<body>

<h3> Add a Student </h3>

</body>

	<form action="StudentControllerServlet" method="GET">

		<input type="hidden" name="command" value="ADD">
		
		<label> First name: </label>
		<input type="text" name="firstName" />
	
		<label> Last name: </label>
		<input type="text" name="lastName" />
		
		<label> Email: </label>
		<input type="text" name="email" />
		
		<label></label>
		<input type="submit" value="Save" class="save"/>
	</form>

</head>

</html>