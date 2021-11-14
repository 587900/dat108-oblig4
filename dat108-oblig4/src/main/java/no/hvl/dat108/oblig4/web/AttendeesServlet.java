package no.hvl.dat108.oblig4.web;

import java.io.IOException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat108.oblig4.Globals;
import no.hvl.dat108.oblig4.LoginUtil;
import no.hvl.dat108.oblig4.User;
import no.hvl.dat108.oblig4.UsersUtil;

@WebServlet(Globals.ATTENDEES_URL)
public class AttendeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsersUtil usersUtil;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!LoginUtil.isLoggedIn(request)) { response.sendRedirect(Globals.LOGIN_URL); return; }
		
		Collection<User> users = usersUtil.getAllUsersSorted();
		request.setAttribute("users", users);
		
		request.getRequestDispatcher(Globals.JSP_ATTENDEES_LOCATION).forward(request, response);
		
	}

}
