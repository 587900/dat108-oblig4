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
<title>Påmeldingsbekreftelse</title>
</head>
<body>
	<h2>Påmeldingsbekreftelse</h2>
	<p>Påmeldingen er mottatt for</p>
	<p>
		&nbsp;&nbsp;&nbsp;${user.firstname}<br />
		&nbsp;&nbsp;&nbsp;${user.lastname}<br />
		&nbsp;&nbsp;&nbsp;${user.cell}<br />
		&nbsp;&nbsp;&nbsp;${user.sex == 'm' ? 'Mann':'Kvinne'}<br />
	</p>
	<a href="deltagerliste">Gå til deltagerlisten</a>
</body>
</html>