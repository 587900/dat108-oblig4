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
import no.hvl.dat108.oblig4.WebValidator;

@WebServlet(Globals.SIGNUP_URL)
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private UsersUtil usersUtil;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("constants", Globals.webConstants);
		boolean loggedIn = LoginUtil.getLoggedInUser(request) != null;
		
		if(request.getParameter("user-registered") != null) {
			if(!loggedIn) { response.sendRedirect(Globals.SIGNUP_URL); return; }
			request.getRequestDispatcher(Globals.FILELOC_JSP_SIGNUP_SUCCESS).forward(request, response);
			return;
		}
		
		if (loggedIn) request.setAttribute("loggedin", true);
		request.getRequestDispatcher(Globals.FILELOC_JSP_SIGNUP).forward(request, response);
		
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
		
		//Note: no need for input sanitization due to WebValidator pattern requirements (illegal symbols not permitted by it)
		boolean error = false;
		if(!WebValidator.firstnameValid(firstname)) 						{ error = true; firstname = ""; }
		if(!WebValidator.lastnameValid(lastname)) 							{ error = true; lastname = ""; }
		if(!WebValidator.cellValid(cell)) 									{ error = true; cell = ""; }
		if(!WebValidator.passwordRepeatedValid(passwordRepeated, password)) { error = true; passwordRepeated = ""; }
		if(!WebValidator.passwordValid(password)) 							{ error = true; password = ""; }	//Note: if password invalid, passwordRepeated may still remain valid. Is this desired behaviour? Assignment does not say.
		if(!WebValidator.sexValid(sex)) 									{ error = true; sex = ""; }
		
		if(error) {
			setErrorAttributes(request, firstname, lastname, cell, password, passwordRepeated, sex);
			respondError(request, response);			
			return;
		}
		
		User user = User.createNewFromPassword(firstname, lastname, cell, password, sex);
		boolean success = usersUtil.addUser(user);
		if(!success) {
			boolean userExists = usersUtil.exists(cell);
			
			if (!userExists) { response.sendError(500); return; }
			
			setErrorAttributes(request, firstname, lastname, cell, password, passwordRepeated, sex);
			respondErrorUserExists(request, response);
			return;
		}
		

		LoginUtil.login(request, user);

		// Redirect to this servlet (get)
		response.sendRedirect(Globals.SIGNUP_URL + "?user-registered");
		
	}
	
	private void setErrorAttributes(HttpServletRequest request, String firstname, String lastname, String cell, String password, String passwordRepeated, String sex) {
		request.setAttribute("firstname", firstname);
		request.setAttribute("lastname", lastname);
		request.setAttribute("cell", cell);
		request.setAttribute("password", password);
		request.setAttribute("passwordRepeated", passwordRepeated);
		request.setAttribute("sex", sex);
	}
	
	private void respondError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("error", true);
		request.setAttribute("constants", Globals.webConstants);
		
		request.getRequestDispatcher(Globals.FILELOC_JSP_SIGNUP).forward(request, response);
	}
	
	private void respondErrorUserExists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("userexists", true);
		request.setAttribute("cell", "");
		respondError(request, response);
	}

}
