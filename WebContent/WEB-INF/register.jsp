<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>
	<article>
		<form:form action="registerUser" method="post" modelAttribute="user">
			<p>Username: <input type="text" name="username"/></p>
			<p>Password: <input type="password" name="password"/></p>
			<input type="submit" value="Register"/>
		</form:form>
		<font color="red">${errorMessage}</font>
	</article>
</body>
</html>