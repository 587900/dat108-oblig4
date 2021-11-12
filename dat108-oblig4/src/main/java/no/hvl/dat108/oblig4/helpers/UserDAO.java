package no.hvl.dat108.oblig4.helpers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.hvl.dat108.oblig4.User;

@Stateless
public class UserDAO extends DAO<User> {
	
	@PersistenceContext(name="fest")
	EntityManager em;
	
	public UserDAO() {
		super(User.class);
	}

}
