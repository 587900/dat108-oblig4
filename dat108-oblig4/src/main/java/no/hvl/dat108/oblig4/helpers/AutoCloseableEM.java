package no.hvl.dat108.oblig4.helpers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AutoCloseableEM implements AutoCloseable {

	private EntityManager em;
	
	private AutoCloseableEM(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public void close() {		
		em.close();
	}
	
	public EntityManager em() {
		return em;
	}
	
	public static AutoCloseableEM from(EntityManagerFactory emf) {
		return new AutoCloseableEM(emf.createEntityManager());
	}

}
