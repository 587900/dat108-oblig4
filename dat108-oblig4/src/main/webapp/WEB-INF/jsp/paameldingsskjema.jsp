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
		let domRepeatPassword = document.getElementById("repeat-password");
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
				<label for="fornavn">Fornavn:</label>
				<!-- Oppgaven spesifiserer ikke om store bokstaver er tillatt etter første tegn i fornavn. -->
				<!-- Beskrivelse på side 10 stemmer ikke med bildet på side 7. -->
				<input type="text" name="firstname" value="${firstname}" required pattern="^[A-ZÆØÅ][A-ZÆØÅa-zæøå -]{1,19}$"/>
				<c:if test="${error == true && empty firstname}"><font color="red">Ugyldig fornavn</font></c:if>
			</div>
			<div class="pure-control-group">
				<label for="etternavn">Etternavn:</label>
				<input type="text" name="lastname" value="${lastname}" required pattern="^[A-ZÆØÅ][A-ZÆØÅa-zæøå-]{1,19}$"/>
				<c:if test="${error == true && empty lastname}"><font color="red">Ugyldig etternavn</font></c:if>
			</div>
			<div class="pure-control-group">
				<label for="mobil">Mobil (8 siffer):</label>
				<input type="text" name="cell" value="${cell}" required pattern="^\d{8}$"/>
				<c:if test="${error == true && empty cell}"><font color="red">Ugyldig mobil</font></c:if>
			</div>
			<div class="pure-control-group">
				<label for="password">Passord:</label>
				<input type="password" name="password" value="${password}" required minlength="8" id="password"/>
				<c:if test="${error == true && empty password}"><font color="red">Passord må være 8 tegn eller mer.</font></c:if>
			</div>
			<div class="pure-control-group">
				<label for="passordRepetert">Passord repetert:</label>
				<input type="password" name="passwordRepeated" value="${passwordRepeated}" required id="repeat-password"/>
				<c:if test="${error == true && empty passwordRepeated}"><font color="red">Passordene må være like.</font></c:if>
			</div>
			<div class="pure-control-group">
				<label for="kjonn">Kjønn:</label>
				<input type="radio" name="sex" value="m" ${sex == "m" ? "checked":""} required/>mann
				<input type="radio" name="sex" value="f" ${sex == "f" ? "checked":""}/>kvinne
				<!-- Denne error-en vil nok aldri forekomme. -->
				<c:if test="${error == true && empty sex}"><font color="red">Du må oppgi kjønn.</font></c:if>
			</div>
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Meld meg på</button>
			</div>
		</fieldset>
	</form>
</body>
</html>