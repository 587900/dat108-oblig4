package no.hvl.dat108.oblig4;

import java.util.Collection;
import java.util.Comparator;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UsersUtil {
	
	//@EJB(mappedName = "LocalUserStorage")
	@EJB(mappedName = "DatabaseUserStorage")
	private UserStorage userStorage;
	
	private static final Comparator<User> ascFirstLast = (u1, u2) -> {
		int score = u1.getFirstname().compareTo(u2.getFirstname());
		if(score != 0) return score;
		score = u1.getLastname().compareTo(u2.getLastname());
		return score;
	};
	
	/** Store a user. Thread-safe. Returns true on success, false otherwise. No duplicates by cell allowed. */
	public boolean addUser(User user) {
		return userStorage.store(user);
	}
	
	public boolean exists(String cell) {
		return userStorage.exists(cell);
	}
	
	public Collection<User> getAllUsersSorted() {
		return userStorage.getAllUsers(ascFirstLast);
	}

	public User lookup(String cell) {
		return userStorage.lookup(cell);
	}

	public User tryGetUser(String cell, String password) {
		User user = lookup(cell);
		if(user == null) return null;
		if(!user.checkPassword(password)) return null;
		return user;
	}

}
