package no.hvl.dat108.oblig4.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat108.oblig4.Globals;

@WebServlet(Globals.WELCOME_URL)
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//We could put SIGNUP_URL (/paamelding) inside web.xml welcome-file-list, however this would cause "duplicate content".
	//There seem to be no way to handle redirects directly in web.xml. See this for reference:
	//https://stackoverflow.com/questions/20177129/use-redirect-instead-of-forward-on-welcome-file
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(Globals.SIGNUP_URL);
	}
	
}
