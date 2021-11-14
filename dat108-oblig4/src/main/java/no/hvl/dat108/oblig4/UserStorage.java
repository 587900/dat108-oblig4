package no.hvl.dat108.oblig4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public abstract class UserStorage {
	
	/** 
	 * Store a user. This must be done in a thread-safe manner such that:
	 * 		There exists no two users with the same cell number.
	 * 		If .store(u), such that adding u would result in a duplicate: do not store u, and return false.
	 * @return true on success, false otherwise. 
	 */
	public abstract boolean store(User user);
	
	/** Get a user by their cell number. */
	public abstract User lookup(String cell);
	
	/** Get all users currently stored. */
	public abstract Collection<User> getAllUsers();
	
	/** @return true if user exists by looking up their cell number. */
	public boolean exists(String cell) { return lookup(cell) != null; }
	
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
