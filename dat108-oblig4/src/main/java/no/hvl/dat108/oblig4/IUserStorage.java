package no.hvl.dat108.oblig4;

import java.util.Collection;

public interface IUserStorage {
	
	// True = success, false = fail
	public boolean store(User user);
	
	// Input sort filter?
	public Collection<User> getAllUsers();
	
	public User lookup(String cell);

}
