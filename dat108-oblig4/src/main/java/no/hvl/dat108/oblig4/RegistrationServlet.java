package no.hvl.dat108.oblig4;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/paamelding")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		request.setAttribute("firstname", "Karl");
//		request.setAttribute("lastname", "Karlsson");
//		request.setAttribute("cell", "");
//		request.setAttribute("password", "");
//		request.setAttribute("passwordRepeated", "");
//		request.setAttribute("sex", "");
//		request.setAttribute("error", true);
		
		request.getRequestDispatcher("WEB-INF/jsp/paameldingsskjema.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String cell = request.getParameter("cell");
		String password = request.getParameter("password");
		String passwordRepeated = request.getParameter("passwordRepeated");
		String sex = request.getParameter("sex");
		
		if(firstname == null || lastname == null || cell == null || password == null || passwordRepeated == null || sex == null) {
			response.sendError(400);
			return;
		}
		
		boolean error = false;
		
		if(!firstname.matches("^[A-ZÆØÅ][A-ZÆØÅa-zæøå -]{1,19}$")) { firstname = ""; error = true; }
		
		if(!lastname.matches("^[A-ZÆØÅ][A-ZÆØÅa-zæøå-]{1,19}$")) { lastname = "";  error = true; }
		
		if(!cell.matches("^\\d{8}$")) { cell = ""; error = true; }
		
		if(password.length() < 8) { password = ""; error = true; }
		
		if(!passwordRepeated.equals(password)) { passwordRepeated = ""; error = true; }
		
		if(!sex.equals("m") && !sex.equals("f")) { sex = ""; error = true; }
		
		if(error) {
			
			request.setAttribute("firstname", firstname);
			request.setAttribute("lastname", lastname);
			request.setAttribute("cell", cell);
			request.setAttribute("password", password);
			request.setAttribute("passwordRepeated", passwordRepeated);
			request.setAttribute("sex", sex);
			request.setAttribute("error", true);
			
			request.getRequestDispatcher("WEB-INF/jsp/paameldingsskjema.jsp").forward(request, response);
			
			return;
		}
		
		User user = User.createNewFromPassword(firstname, lastname, cell, password, sex);
	}

}
