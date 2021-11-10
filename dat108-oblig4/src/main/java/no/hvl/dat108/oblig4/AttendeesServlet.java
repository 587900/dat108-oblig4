package no.hvl.dat108.oblig4;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO Where we are now: Fjern grønnfarge på andre deltakere

@WebServlet("/deltagerliste")
public class AttendeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!LoginUtil.isLoggedIn(request)) { response.sendRedirect("/logginn"); return; }
		
		Collection<User> users = UsersUtil.getAllUsers();
		request.setAttribute("users", users);
		
		request.getRequestDispatcher("WEB-INF/jsp/deltagerliste.jsp").forward(request, response);
		
	}

}
