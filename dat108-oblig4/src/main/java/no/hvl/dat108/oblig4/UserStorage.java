package no.hvl.dat108.oblig4;

import java.util.Collection;

public class UserStorage {
	
	// True = success, false = fail
	public boolean store(User user) {
		return true;
	}
	
	public boolean canRegister(User user) {
		return false;
	}
	
	// Input sort filter?
	public Collection<User> getAllUsers() {
		return null;
	}
	
	public User lookup(String cell) {
		return null;
	}

}
