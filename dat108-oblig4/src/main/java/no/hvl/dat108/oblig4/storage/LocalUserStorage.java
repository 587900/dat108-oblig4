package no.hvl.dat108.oblig4.storage;

import java.util.Collection;
import java.util.HashMap;

import javax.ejb.Stateless;

import no.hvl.dat108.oblig4.User;

@Stateless
public class LocalUserStorage extends UserStorage {

	//We could use ConcurrentHashMap here, but synchronization is required for .store regardless (and thereby getAllUsers and lookup as well), so it would be pointless.
	private static HashMap<String, User> users = new HashMap<>();
	
	static {
		LocalUserStorage storage = new LocalUserStorage();
		storage.store(User.createNewFromPassword("Sjølvaste", "Leidvin", "99999999", "99999999", "m"));
		storage.store(User.createNewFromPassword("Kjetil", "Dverge", "11111111", "11111111", "m"));
		storage.store(User.createNewFromPassword("Llars Erik", "Birkefjell", "22222222", "22222222", "f"));
		storage.store(User.createNewFromPassword("Lima", "Lima", "33333333", "33333333", "f"));
		storage.store(User.createNewFromPassword("Sjølvaste", "Kristoffer", "44444444", "44444444", "m"));
		storage.store(User.createNewFromPassword("Stein", "Abeltun", "55555555", "55555555", "m"));
		storage.store(User.createNewFromPassword("Are", "Clementin", "66666666", "66666666", "f"));
		storage.store(User.createNewFromPassword("Chris P", "Bacon", "77777777", "77777777", "m"));
		storage.store(User.createNewFromPassword("Janice", "Keihanaikukauaka", "88888888", "88888888", "f"));
		storage.getAllUsers().forEach(System.out::println);
	}
	
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
