package no.hvl.dat108.oblig4;

import java.util.Collection;
import java.util.Comparator;

public class UsersUtil {
	
//	private static final UserStorage userStorage = new LocalUserStorage();
	private static final UserStorage userStorage = new DatabaseUserStorage();
	
	private static final Comparator<User> ascFirstLast = (u1, u2) -> {
		int score = u1.getFirstname().compareTo(u2.getFirstname());
		if(score != 0) return score;
		score = u1.getLastname().compareTo(u2.getLastname());
		return score;
	};
			
	
	// TODO Vurdere å ta vekk if
	// TODO Trådsikkerhet
	public static boolean addUser(User user) {
		if(exists(user.getCell())) return false;
		return userStorage.store(user);
	}
	
	public static boolean exists(String cell) {
		return userStorage.exists(cell);
	}
	
	public static Collection<User> getAllUsersSorted() {
		return userStorage.getAllUsers(ascFirstLast);
	}


	public static User lookup(String cell) {
		return userStorage.lookup(cell);
	}

	public static User tryGetUser(String cell, String password) {
		User user = lookup(cell);
		if(user == null) return null;
		if(!user.checkPassword(password)) return null;
		return user;
	}

}
