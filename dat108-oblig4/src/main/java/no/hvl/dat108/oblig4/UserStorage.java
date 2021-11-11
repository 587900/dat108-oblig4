package no.hvl.dat108.oblig4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public abstract class UserStorage {
	
	// True = success, false = fail
	public abstract boolean store(User user);
	
	public abstract User lookup(String cell);
	
	public boolean exists(String cell) { return lookup(cell) != null; }

	public abstract Collection<User> getAllUsers();
	
	/**
	 * Get all users and sort by comparator.
	 * @param comparator
	 * @return
	 */
	public Collection<User> getAllUsers(Comparator<User> comparator) {
		ArrayList<User> a = new ArrayList<>(getAllUsers());
		Collections.sort(a, comparator);
		return a;
	}
}
