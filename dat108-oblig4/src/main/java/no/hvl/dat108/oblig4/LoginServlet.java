package no.hvl.dat108.oblig4;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat108.oblig4.helpers.UserDAO;

// TODO jsp password: Legg inn variabel for lengde i .xml
// TODO Side dersom allerede logget inn
// TODO Outline input fields med grønn/rød

@WebServlet("/logginn")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//private UsersUtil usersUtil = UsersUtil.newInstance();
	//private UsersUtil usersUtil = new UsersUtil();
	@EJB
	private UsersUtil usersUtil;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("wrong") != null) request.setAttribute("wrong", true);
		
		request.getRequestDispatcher("WEB-INF/jsp/logginn.jsp").forward(request, response);
		
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
			response.sendRedirect("/logginn?wrong");
			return;
		}
		
		LoginUtil.login(request, user);
		response.sendRedirect("/deltagerliste");
	}

}
