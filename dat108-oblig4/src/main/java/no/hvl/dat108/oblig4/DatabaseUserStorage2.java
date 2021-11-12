package no.hvl.dat108.oblig4;

import java.util.Collection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import no.hvl.dat108.oblig4.helpers.AutoCloseableEM;

public class DatabaseUserStorage2 extends UserStorage {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("fest");

	@Override
	public boolean store(User user) {

		EntityTransaction t = null;

		try (AutoCloseableEM em = AutoCloseableEM.from(emf)) {
			t = em.em().getTransaction();
			t.begin();
			em.em().persist(user);
			t.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (t != null && t.isActive())
				t.rollback();
			return false;
		}
		return true;
	}

	@Override
	public User lookup(String cell) {
		try (AutoCloseableEM em = AutoCloseableEM.from(emf)) {
			return em.em().find(User.class, cell);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Collection<User> getAllUsers() {
		try (AutoCloseableEM em = AutoCloseableEM.from(emf)) {
			return em.em().createQuery("SELECT a FROM users a", User.class).getResultList(); 
		}
	}

	// TODO Kjetil fix
//	@Override
//	@Query("SELECT COUNT(u) = 1 FROM users u WHERE cell = ?1")
//	public boolean exists(String cell);

}