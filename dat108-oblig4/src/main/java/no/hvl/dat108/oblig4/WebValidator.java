package no.hvl.dat108.oblig4;

public class WebValidator {
	
	public static boolean firstnameValid(String firstname) {
		return firstname.matches("^[A-ZÆØÅ][A-ZÆØÅa-zæøå -]{1,19}$");
	}
	
	public static boolean lastnameValid(String lastname) {
		return lastname.matches("^[A-ZÆØÅ][A-ZÆØÅa-zæøå-]{1,19}$");
	}
	
	public static boolean cellValid(String cell) {
		return cell.matches("^\\d{8}$");
	}
	
	public static boolean passwordValid(String password) {
		return password.length() >= 8;
	}
	
	public static boolean passwordRepeatedValid(String passwordRepeated, String password) {
		return passwordRepeated.equals(password);
	}
	
	public static boolean sexValid(String sex) {
		return sex.equals("m") || sex.equals("f");
	}

}
