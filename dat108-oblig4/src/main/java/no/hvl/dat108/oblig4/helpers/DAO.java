package no.hvl.dat108.oblig4.helpers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class DAO<T> {
	
	private Class<T> clazz;
	
	@PersistenceContext(name="fest")
	EntityManager em;
	
	public DAO(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public T findOne(Object id) {
		return em.find(clazz, id);
	}
	
	public void create(T entity) {
		em.persist(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return em.createQuery("from " + clazz.getName()).getResultList();
	}

}
