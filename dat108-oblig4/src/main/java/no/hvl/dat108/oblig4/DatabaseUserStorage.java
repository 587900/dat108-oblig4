package no.hvl.dat108.oblig4;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import no.hvl.dat108.oblig4.helpers.UserDAO;

@Stateless
public class DatabaseUserStorage extends UserStorage {

	@EJB
	private UserDAO userDAO;
	
	@Override
	public boolean store(User user) {
		return userDAO.create(user);
	}

	@Override
	public User lookup(String cell) {
		return userDAO.findOne(cell);
	}

	@Override
	public Collection<User> getAllUsers() {
		return userDAO.findAll();
	}

}