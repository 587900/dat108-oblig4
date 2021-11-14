package no.hvl.dat108.oblig4;

public class Globals {
	
	public static final WebConstants webConstants = new Globals.WebConstants();

	public static final String WEB_URL_PREFIX = "";
	
	public static final String ATTENDEES_URL = "/deltagerliste";
	public static final String LOGIN_URL = "/logginn";
	public static final String LOGOUT_URL = "/loggut";
	public static final String SIGNUP_URL = "/paamelding";
	public static final String WELCOME_URL = "/";				//never used
	
	public static final String FILELOC_JSP_ATTENDEES = "WEB-INF/jsp/attendees_list.jsp";
	public static final String FILELOC_JSP_LOGIN = "WEB-INF/jsp/login.jsp";
	public static final String FILELOC_JSP_LOGOUT = "WEB-INF/jsp/logout.jsp";
	public static final String FILELOC_JSP_SIGNUP = "WEB-INF/jsp/signup.jsp";
	public static final String FILELOC_JSP_SIGNUP_SUCCESS = "WEB-INF/jsp/signup_success.jsp";
	
	public static final String HASHUTIL_SALT_ALGORITHM = "SHA1PRNG";
	public static final String HASHUTIL_HASH_ALGORITHM = "PBKDF2WithHmacSHA256";
	
	public static class WebConstants {
		//expression language only works by getters
		public String getWEB_ATTENDEES_URL() { return WEB_URL_PREFIX + ATTENDEES_URL; }
		public String getWEB_LOGIN_URL() { return WEB_URL_PREFIX + LOGIN_URL; }
		public String getWEB_LOGOUT_URL() { return WEB_URL_PREFIX + LOGOUT_URL; }
		public String getWEB_SIGNUP_URL() { return WEB_URL_PREFIX + SIGNUP_URL; }
		public String getWEB_WELCOME_URL() { return WEB_URL_PREFIX + WELCOME_URL; }	//never used
	}

}
