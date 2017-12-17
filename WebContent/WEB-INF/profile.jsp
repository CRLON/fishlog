<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<c:url value="/resources/javascript/profile.js"/>"></script>
<title>Fishlog profile</title>

</head>
<body>
<h1>Fishlog profile</h1>
<h2>Welcome ${currentUser.username}! </h2>
<button onclick="showPasswordEdit()">Edit profile</button>
<div id="editProfile" style="display: none;">
	<form action="changePassword" method="post">
  		Current password: <input type="password" name="currentPassword"/>
  		New password: <input type="password" name="newPassword"/>
  		<input type="submit" value="Change password"/>
  	</form>
</div> 

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
<hr/>
</c:if>
	<article>
		<form:form action="addFish" method="post" modelAttribute="fish">
			<p>Species: <input type="text" name="species" min="1" required/>
			Weight: <input type="text" name="weight"/> Grams.
			Length: <input type="text" name="length" pattern="\d*" min="1" required/> Centimeter.</p>
			<p><input type="checkbox" name="includeCurrentDate"/> Include current date?</p>
			<input type="submit" value="Add"/>
		</form:form>
	</article>
<font color="red">${errorMessage}</font>
<font color="green">${successMessage}</font>
</body>	
</html>