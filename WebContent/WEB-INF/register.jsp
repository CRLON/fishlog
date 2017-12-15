<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<article>
		<form action="registerUser" method="post">
			<p>Username: <input type="text" name="username"/></p>
			<p>Password: <input type="password" name="password"/></p>
			<input type="submit" value="Register"/>
		</form>
		<font color="red">${errorMessage}</font>
	</article>
</body>
</html>