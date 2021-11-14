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
<title>Påmelding</title>
<style>
input[type="text"], input[type="password"] {
	border-color: green!important;
}
input[type="text"]:invalid, input[type="password"]:invalid {
	border-color: red!important;
}
input[type="password"].password-weak {
	border-color: yellow!important;
}
.error-tag {
	display: inline-block;
	color: red;
}

</style>
<script>
	
	class PasswordValidator {
		// Returns strength level (1 = weak, 2 = strong)
		getPasswordStrength(password) {
			if(password.length <= 14) return 1;
			else return 2;
		}
	}
	
	class DOMPasswordWatcher {
		constructor(validator) {
			this.validator = validator;
		}
		
		watch(dom) {
			dom.addEventListener("input", this.onChange.bind(this));
		}
		
		onChange(e) {
			e.target.classList.remove("password-weak");
			if(e.target.validity.valid) {
				let password = e.target.value;
				let strength = this.validator.getPasswordStrength(password);
				if(strength == 1) {
					// Weak
					e.target.classList.add("password-weak");
				} 
			}
		}
	}
	
	class PasswordDOMComparator {
		listen(domPassword, domRepeatPassword) {
			let func = this.onChange.bind(this, domPassword, domRepeatPassword);
			domPassword.addEventListener("input", func);
			domRepeatPassword.addEventListener("input", func);
		}
		
		onChange(domPassword, domRepeatPassword, e) {
			let equal = (domPassword.value === domRepeatPassword.value);
			if (equal) {
				domRepeatPassword.setCustomValidity("");
			} else {
				domRepeatPassword.setCustomValidity("Repetert passord er feil!");
			}
		}
	}
	
	window.onload = function() {
		let validator = new PasswordValidator();
		let passwordWatcher = new DOMPasswordWatcher(validator);
		let passwordComparator = new PasswordDOMComparator();
		
		let domPassword = document.getElementById("password");
		let domRepeatPassword = document.getElementById("passwordRepeated");
		passwordWatcher.watch(domPassword);
		passwordComparator.listen(domPassword, domRepeatPassword);
		
		let event = new Event("input", {});
		domPassword.dispatchEvent(event);
	}
	
</script>
</head>
<body>
	<h2>Påmelding</h2>
	<form method="post" class="pure-form pure-form-aligned">
		<fieldset>
			<div class="pure-control-group">
				<label for="firstname">Fornavn:</label>
				<!-- Oppgaven spesifiserer ikke om store bokstaver er tillatt etter første tegn i fornavn. -->
				<!-- Beskrivelse på side 10 stemmer ikke med bildet på side 7. -->
				<!-- Oppgaven nevner heller ikke noe om ikke-latinske bokstaver utenom æ, ø og å -->
				<input id="firstname" type="text" name="firstname" value="${firstname}" required pattern="^[A-ZÆØÅ][A-ZÆØÅa-zæøå -]{1,19}$"/>
				<c:if test="${error == true && empty firstname}"><div class="error-tag">Ugyldig fornavn</div></c:if>
			</div>
			<div class="pure-control-group">
				<label for="lastname">Etternavn:</label>
				<input id="lastname" type="text" name="lastname" value="${lastname}" required pattern="^[A-ZÆØÅ][A-ZÆØÅa-zæøå-]{1,19}$"/>
				<c:if test="${error == true && empty lastname}"><div class="error-tag">Ugyldig etternavn</div></c:if>
			</div>
			<div class="pure-control-group">
				<label for="cell">Mobil (8 siffer):</label>
				<input id="cell" type="text" name="cell" value="${cell}" required pattern="^\d{8}$"/>
				<c:if test="${error == true && userexists != true && empty cell}"><div class="error-tag">Ugyldig mobil</div></c:if>
				<c:if test="${error == true && userexists == true}"><div class="error-tag">Mobilnummer allerede registrert.</div></c:if>
			</div>
			<div class="pure-control-group">
				<label for="password">Passord:</label>
				<input id="password" type="password" name="password" value="${password}" required minlength="8"/>
				<c:if test="${error == true && empty password}"><div class="error-tag">Passord må være 8 tegn eller mer.</div></c:if>
			</div>
			<div class="pure-control-group">
				<label for="passwordRepeated">Passord repetert:</label>
				<input id="passwordRepeated" type="password" name="passwordRepeated" value="${passwordRepeated}" required/>
				<c:if test="${error == true && empty passwordRepeated}"><div class="error-tag">Passordene må være like.</div></c:if>
			</div>
			<div class="pure-control-group">
				<label for="sex">Kjønn:</label>
				<input id="sex" type="radio" name="sex" value="m" ${sex == "m" ? "checked":""} required/>mann
				<input type="radio" name="sex" value="f" ${sex == "f" ? "checked":""}/>kvinne
				<!-- Denne error-en vil nok aldri forekomme. -->
				<c:if test="${error == true && empty sex}"><div class="error-tag">Du må oppgi kjønn.</div></c:if>
			</div>
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Meld meg på</button>
			</div>
		</fieldset>
	</form>
	
	<c:if test="${loggedin != true}"><p>Allerede registrert? <a href="logginn">Logg inn her.</a></p></c:if>
	<c:if test="${loggedin == true}"><p>Du er allerede logget inn. Er du sikker på du vil melde på en ny bruker? For å gå til deltagerliste, <a href="deltagerliste">klikk her</a></p></c:if>

</body>
</html>