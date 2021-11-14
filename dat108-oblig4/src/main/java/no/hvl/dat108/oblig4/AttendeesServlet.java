package no.hvl.dat108.oblig4;

import java.io.IOException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deltagerliste")
public class AttendeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static { 
		/*
		UsersUtil.addUser(User.createNewFromPassword("Sjølvaste", "Leidvin", "99999999", "99999999", "m"));
		UsersUtil.addUser(User.createNewFromPassword("Kjetil", "Dverge", "11111111", "11111111", "m"));
		UsersUtil.addUser(User.createNewFromPassword("Llars Erik", "Birkefjell", "22222222", "22222222", "f"));
		UsersUtil.addUser(User.createNewFromPassword("Lima", "Lima", "33333333", "33333333", "f"));
		UsersUtil.addUser(User.createNewFromPassword("Sjølvaste", "Kristoffer", "44444444", "44444444", "m"));
		UsersUtil.addUser(User.createNewFromPassword("Stein", "Abeltun", "55555555", "55555555", "m"));
		UsersUtil.addUser(User.createNewFromPassword("Are", "Clementin", "66666666", "66666666", "f"));
		UsersUtil.addUser(User.createNewFromPassword("Chris", "Bacon", "77777777", "77777777", "m"));
		UsersUtil.addUser(User.createNewFromPassword("Janice", "Keihanaikukauaka", "88888888", "88888888", "f"));
		UsersUtil.getAllUsersSorted().forEach(System.out::println);
		*/
	}
	
	@EJB
	private UsersUtil usersUtil;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!LoginUtil.isLoggedIn(request)) { response.sendRedirect("/logginn"); return; }
		
		Collection<User> users = usersUtil.getAllUsersSorted();
		request.setAttribute("users", users);
		
		request.getRequestDispatcher("WEB-INF/jsp/deltagerliste.jsp").forward(request, response);
		
	}

}
