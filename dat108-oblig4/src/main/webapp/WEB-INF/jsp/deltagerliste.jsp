<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Deltagerliste</title>
</head>
<body>
	<h2>Deltagerliste</h2>
	<table class="pure-table">
		<tr bgcolor="#cccccc">
			<th>Kjønn</th>
			<th align="left">Navn</th>
			<th align="left">Mobil</th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr bgcolor="#aaffaa">
				<td align="center">${user.sex == 'm' ? '&#9794;':'&#9792;'}</td>
				<td>${user.firstname} ${user.lastname}</td>
				<td>${user.cell}</td>
			</tr>
		</c:forEach>
	</table>
	<p>
		<a href="loggut">Ferdig</a>
	</p>
</body>
</html>