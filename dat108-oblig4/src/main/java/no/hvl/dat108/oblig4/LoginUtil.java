package no.hvl.dat108.oblig4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUtil {
	
	public static boolean isLoggedIn(HttpServletRequest request) {
		return getLoggedInUser(request) != null;
	}
	
	public static boolean login(HttpServletRequest request, User user) {
		logout(request);
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(60); // TODO Read from file
		session.setAttribute("user", user);
		return true;
	}
	
	// true = was logged in, false = was not logged in.
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
