package no.hvl.dat108.oblig4;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUtil {
	
	private static Integer maxInactiveIntervalSeconds;
	static {
		try {
			Context ctx = new InitialContext();
			Context env = (Context)ctx.lookup("java:comp/env");
			maxInactiveIntervalSeconds = (Integer) env.lookup("max-inactive-interval-seconds");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static boolean isLoggedIn(HttpServletRequest request) {
		return getLoggedInUser(request) != null;
	}
	
	public static boolean login(HttpServletRequest request, User user) {
		logout(request);
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(maxInactiveIntervalSeconds);
		session.setAttribute("user", user);
		return true;
	}
	
	/** @return true if the user was logged in, false if not. */
	public static boolean logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null) return false;
		session.invalidate();
		return true;
	}
	
	public static User getLoggedInUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null) return null;
		return (User) session.getAttribute("user");
	}

}
