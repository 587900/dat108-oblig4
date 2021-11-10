package no.hvl.dat108.oblig4;

import java.util.Collection;

public class UsersUtil {
	
	public static final IUserStorage userStorage = new LocalUserStorage();
	
	// TODO Vurdere å ta vekk if
	public static boolean addUser(User user) {
		if(exists(user.getCell())) return false;
		return userStorage.store(user);
	}
	
	public static boolean exists(String cell) {
		return lookup(cell) != null;
	}
	
	public static Collection<User> getAllUsers() {
		return userStorage.getAllUsers();
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
