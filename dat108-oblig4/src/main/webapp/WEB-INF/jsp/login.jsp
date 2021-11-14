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
<style>
	input[type="text"], input[type="password"] {
		border-color: green!important;
	}
	input[type="text"]:invalid, input[type="password"]:invalid {
		border-color: red!important;
	}
</style>
</head>
<body>
	<h2>Logg inn</h2>
	<p>Det er kun registrerte deltagere som får se deltagerlisten.</p>
	<p>For å registrere deg, <a href="${constants.WEB_SIGNUP_URL}">klikk her</a></p>
	<c:if test="${loggedin == true}"><p>Du er allerede logget inn. For å gå til deltagerlisten, <a href="${constants.WEB_ATTENDEES_URL}">klikk her</a></p></c:if>
	<p>
		<c:if test="${wrong == true}"><font color="red">Ugyldig mobilnummer og/eller passord</font></c:if>
	</p>
	<form method="post" class="pure-form pure-form-aligned">
		<fieldset>
			<div class="pure-control-group">
				<label for="cell">Mobil:</label>
				<input id="cell" type="text" name="cell" required pattern="^\d{8}$" title="Må vere et 8-sifret tall"/>
			</div>
			<div class="pure-control-group">
				<label for="password">Passord:</label>
				<input id="password" type="password" name="password" required pattern=".{8,}" title="Alle passord er på minst 8 bokstaver"/>
			</div>
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Logg
					inn</button>
			</div>
		</fieldset>
	</form>

</body>
</html>