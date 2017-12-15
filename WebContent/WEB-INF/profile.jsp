<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fishlog profile</title>

</head>
<body>
<h1>Fishlog profile</h1>
<h2>Welcome ${currentUser}! </h2>
<hr/>
<c:if test="${not empty currentUserFishList}">
<form action="deleteFish" method="post">
	<table>
		<tr>
			<th align="center">Species </th>
			<th align="center">Weight </th>
			<th align="center">Length </th>
		</tr>
		<c:forEach var="fishList" items="${currentUserFishList}" varStatus="count">
			<tr>
				<td align="center">${fishList.species}</td>
				<td align="center">${fishList.weight} grams</td>
				<td align="center">${fishList.length} cm</td>
				<td><input type="checkbox" name="deleteSelected" value="${count.index}"/></td>
				</tr>
		</c:forEach>
	</table>
	<input type="submit" value="Delete"/>
	</form>
</c:if>
<hr/>
	<article>
		<form action="addFish" method="post" accept-charset="UTF-8">
			<p>Species: <input type="text" name="species"/>
			Weight: <input type="text" name="weight"/> Grams
			Length: <input type="text" name="length"/> Centimeter</p>
			<p><input type="checkbox" name="includeCurrentDate"/> Include current date?</p>
			<input type="submit" value="Add"/>
		</form>
	</article>
<font color="red">${errorMessage}</font>
<font color="green">${successMessage}</font>
</body>	
</body>
</html>