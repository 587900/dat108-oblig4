package no.hvl.dat108.oblig4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class WebValidatorTest {
	
	@Test
	public void firstnameTest() {
		assertTrue(WebValidator.firstnameValid("Lima"));
		assertTrue(WebValidator.firstnameValid("Kjetil"));
		assertTrue(WebValidator.firstnameValid("Kristoffer"));
		assertTrue(WebValidator.firstnameValid("Lars Erik"));
		assertTrue(WebValidator.firstnameValid("Kjell-Odleif"));
		assertFalse(WebValidator.firstnameValid("per"));
		assertFalse(WebValidator.firstnameValid("123"));
		assertFalse(WebValidator.firstnameValid("Atle den 5."));
		assertTrue(WebValidator.firstnameValid("Atle den femte"));
		assertFalse(WebValidator.firstnameValid("атле ден фемте"));
		assertFalse(WebValidator.firstnameValid("переведено"));
		assertFalse(WebValidator.firstnameValid("✨💕 Sofia 😍✨"));
		assertTrue(WebValidator.firstnameValid("ÆØÅæøå-"));
	}
	
	@Test
	public void lastnameTest() {
		assertTrue(WebValidator.lastnameValid("Aliar"));
		assertTrue(WebValidator.lastnameValid("Berg"));
		assertTrue(WebValidator.lastnameValid("Vågstøl"));
		assertTrue(WebValidator.lastnameValid("Birkeland"));
		assertFalse(WebValidator.lastnameValid("Berge Sand"));
		assertFalse(WebValidator.lastnameValid("thorsen"));
		assertFalse(WebValidator.lastnameValid("456"));
		assertFalse(WebValidator.lastnameValid("i den 8. rekken"));
		assertTrue(WebValidator.lastnameValid("Atle-den-femte"));
		assertFalse(WebValidator.lastnameValid("атле ден фемте"));
		assertFalse(WebValidator.lastnameValid("переведено"));
		assertFalse(WebValidator.lastnameValid("✨💕 Sofia 😍✨"));
		assertTrue(WebValidator.lastnameValid("ÆØÅæøå-"));
	}
	
	@Test
	public void cellTest() {
		assertTrue(WebValidator.cellValid("97162770"));
		assertFalse(WebValidator.cellValid("971 62 770"));
		assertFalse(WebValidator.cellValid("971-62-770"));
		assertFalse(WebValidator.cellValid("555-55-555"));
		assertTrue(WebValidator.cellValid("55555555"));
		assertFalse(WebValidator.cellValid("555555555")); // No americans allowed
		assertFalse(WebValidator.cellValid("telefon"));
		assertFalse(WebValidator.cellValid("5️⃣5️⃣3️⃣1️⃣8️⃣0️⃣0️⃣8️⃣"));
	}
	
	@Test
	public void passwordTest() {
		assertTrue(WebValidator.passwordValid("12345678"));
		assertTrue(WebValidator.passwordValid("abcdefghijklm"));
		assertFalse(WebValidator.passwordValid("1234567"));
		assertFalse(WebValidator.passwordValid("abcd"));
	}
	
	@Test
	public void passwordRepeatedTest() {
		assertTrue(WebValidator.passwordRepeatedValid("12345678", "12345678"));
		assertTrue(WebValidator.passwordRepeatedValid("", ""));
		assertTrue(WebValidator.passwordRepeatedValid("abcdefgh", "abcdefgh"));
		assertTrue(WebValidator.passwordRepeatedValid("abc", "abc"));
		assertFalse(WebValidator.passwordRepeatedValid("12345678", "112345678"));
		assertFalse(WebValidator.passwordRepeatedValid("jeg husker ikke:(", "abcd"));
		assertFalse(WebValidator.passwordRepeatedValid("abcd", "dcba"));
	}
	
	@Test
	public void sexTest() {
		assertTrue(WebValidator.sexValid("m"));
		assertTrue(WebValidator.sexValid("f"));
		assertFalse(WebValidator.sexValid("M"));
		assertFalse(WebValidator.sexValid("F"));
		assertFalse(WebValidator.sexValid("mann"));
		assertFalse(WebValidator.sexValid("kvinne"));
		assertFalse(WebValidator.sexValid("☃️"));
	}
}
