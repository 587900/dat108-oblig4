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

		// TODO #1: Vurdér PRG på feilmeldinger
		// TODO #2: Hvis du er logget inn, vis en spesiell melding
		// TODO #3: User input sanitazation
		// TODO #4: Sjekk ny HTML
		
		if(request.getParameter("user-registered") != null) {
			User user = LoginUtil.getLoggedInUser(request);
			if(user == null) { response.sendRedirect("/paamelding"); return; }
			request.getRequestDispatcher("WEB-INF/jsp/paameldingsbekreftelse.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("WEB-INF/jsp/paameldingsskjema.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
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
		
		if(!WebValidator.firstnameValid(firstname)) { firstname = ""; error = true; }
		if(!WebValidator.lastnameValid(lastname)) { lastname = "";  error = true; }
		if(!WebValidator.cellValid(cell)) { cell = ""; error = true; }
		if(!WebValidator.passwordRepeatedValid(passwordRepeated, password)) { passwordRepeated = ""; error = true; }
		if(!WebValidator.passwordValid(password)) { password = ""; error = true; }
		if(!WebValidator.sexValid(sex)) { sex = ""; error = true; }
		
		if(UsersUtil.exists(cell)) { request.setAttribute("userexists", true); cell = ""; error = true; }
		
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
		boolean success = UsersUtil.addUser(user);
		if(!success) {
			response.sendError(500);
			return;
		}
		
		LoginUtil.login(request, user);
		
		// Redirect to this servlet (get)
		response.sendRedirect("/paamelding?user-registered");
		
	}

}
