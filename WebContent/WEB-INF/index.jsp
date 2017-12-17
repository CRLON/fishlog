<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fishlog</title>
</head>
<body>
	<article>
		<form action="login" method="post">
			<p>Username: <input type="text" name="username"/></p>
			<p>Password: <input type="password" name="password"/></p>
			<input type="submit" value="Login"/>
		</form>
		<form action="register" method="get">
			<input type="submit" value="Register"/>
		</form>
	</article>
<font color="red">${errorMessage}</font>
<font color="green">${successMessage}</font>
</body>
</html>