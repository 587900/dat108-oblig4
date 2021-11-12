package no.hvl.dat108.oblig4;

import java.util.Collection;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import no.hvl.dat108.oblig4.helpers.UserDAO;

@ManagedBean
public class DatabaseUserStorage extends UserStorage {

	@EJB
	private UserDAO userDAO;
	
	@PostConstruct
	public void init() {
		userDAO = new UserDAO();
		System.out.println("init");
	}
	
	public DatabaseUserStorage() {
		init();
	}
	
	@Override
	public boolean store(User user) {
		userDAO.create(user);
		return true;
	}

	@Override
	public User lookup(String cell) {
		return userDAO.findOne(cell);
	}

	@Override
	public Collection<User> getAllUsers() {
		return userDAO.findAll();
	}

	// TODO Kjetil fix
//	@Override
//	@Query("SELECT COUNT(u) = 1 FROM users u WHERE cell = ?1")
//	public boolean exists(String cell);

}