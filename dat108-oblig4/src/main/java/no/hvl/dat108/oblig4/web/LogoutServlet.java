package no.hvl.dat108.oblig4.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat108.oblig4.Globals;
import no.hvl.dat108.oblig4.LoginUtil;

@WebServlet(Globals.LOGOUT_URL)
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LoginUtil.logout(request);
		
		request.setAttribute("constants", Globals.webConstants);
		request.getRequestDispatcher(Globals.FILELOC_JSP_LOGOUT).forward(request, response);
		
	}
}
