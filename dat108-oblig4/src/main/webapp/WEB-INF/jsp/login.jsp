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
<title>Logg inn</title>
</head>
<body>
	<h2>Logg inn</h2>
	<p>Det er kun registrerte deltagere som får se deltagerlisten.</p>
	<p>For å registrere deg, <a href="/paamelding">klikk her</a></p>
	<p>
		<c:if test="${wrong == true}"><font color="red">Ugyldig mobilnummer og/eller passord</font></c:if>
	</p>
	<form method="post" class="pure-form pure-form-aligned">
		<fieldset>
			<div class="pure-control-group">
				<label for="cell">Mobil:</label>
				<input id="cell" type="text" name="cell" required pattern="^\d{8}$"/>
			</div>
			<div class="pure-control-group">
				<label for="password">Passord:</label>
				<input id="password" type="password" name="password" required minlength="8"/>
			</div>
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Logg
					inn</button>
			</div>
		</fieldset>
	</form>

</body>
</html>