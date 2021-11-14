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
	
	//Note: The userStorage-mentioned exception cannot be caught here, since it is EJB-driven. It may only be caught by the first non-EJB in the chain (generally servlets).
	/** Store a user. Thread-safe. Returns true on success, false otherwise. No duplicates by cell allowed. May throw exceptions to signal failure. */
	public boolean addUser(User user) throws Exception {
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
	
	
	public static class Helper {
		
		/** short-hand for try-catch about addUser. */
		public static boolean addUserHandleException(User user, UsersUtil instance) {
			try { return instance.addUser(user); } catch (Exception e) { return false; }
		}
		
	}

}
