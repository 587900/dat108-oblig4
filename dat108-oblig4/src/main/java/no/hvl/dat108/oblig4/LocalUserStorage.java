package no.hvl.dat108.oblig4;

import java.util.Collection;
import java.util.HashMap;

import javax.ejb.Stateless;

@Stateless
public class LocalUserStorage extends UserStorage {

	//We could use ConcurrentHashMap here, but synchronization is required for .store regardless (and thereby getAllUsers and lookup as well), so it would be pointless.
	private static HashMap<String, User> users = new HashMap<>();
	
	@Override
	public boolean store(User user) {
		synchronized(users) {
			if (users.containsKey(user.getCell())) return false;
			users.put(user.getCell(), user);
			return true;
		}
	}

	@Override
	public Collection<User> getAllUsers() {
		synchronized(users) { return users.values(); }
	}

	@Override
	public User lookup(String cell) {
		synchronized (users) { return users.get(cell); }
	}
	
}
