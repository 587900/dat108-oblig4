package no.hvl.dat108.oblig4;

import java.util.Collection;
import java.util.HashMap;

public class LocalUserStorage implements IUserStorage {

	private HashMap<String, User> users;
	
	public LocalUserStorage() {
		users = new HashMap<>();
	}
	
	@Override
	public boolean store(User user) {
		users.put(user.getCell(), user);
		return true;
	}

	@Override
	public Collection<User> getAllUsers() {
		return users.values();
	}

	@Override
	public User lookup(String cell) {
		return users.get(cell);
	}
	
	

}
