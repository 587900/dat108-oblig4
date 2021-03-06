package no.hvl.dat108.oblig4.web;

import java.io.IOException;

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

@WebServlet(Globals.LOGIN_URL)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private UsersUtil usersUtil;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("wrong") != null) request.setAttribute("wrong", true);
		if (LoginUtil.isLoggedIn(request)) request.setAttribute("loggedin", true);
		
		request.setAttribute("constants", Globals.webConstants);
		request.getRequestDispatcher(Globals.FILELOC_JSP_LOGIN).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String cell = request.getParameter("cell");
		String password = request.getParameter("password");
		
		if(cell == null || password == null) {
			response.sendError(400);
			return;
		}
		
		User user = usersUtil.tryGetUser(cell, password);
		if(user == null) {
			response.sendRedirect(Globals.LOGIN_URL + "?wrong");
			return;
		}
		
		LoginUtil.login(request, user);
		response.sendRedirect(Globals.ATTENDEES_URL);
	}

}
