<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="no">
<head>
<meta charset="UTF-8">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Deltagerliste</title>
<style>
.align-left { text-align: left; }
.align-center { text-align: center; }
.header-row { background-color: #cccccc; }
.selected { background-color: #aaffaa; }
</style>
</head>
<body>
	<h2>Deltagerliste</h2>
	<table class="pure-table">
		<tr class="header-row">
			<th>Kjønn</th>
			<th class="align-left">Navn</th>
			<th class="align-left">Mobil</th>
		</tr>
		<c:forEach var="attendee" items="${users}">
			<tr class="${attendee.cell == user.cell ? ' selected':''}">
				<td class="align-center">${attendee.sex == 'm' ? '&#9794;':'&#9792;'}</td>
				<td>${attendee.firstname} ${attendee.lastname}</td>
				<td>${attendee.cell}</td>
			</tr>
		</c:forEach>
	</table>
	<p>
		<a href="loggut">Ferdig</a>
	</p>
</body>
</html>