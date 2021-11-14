package no.hvl.dat108.oblig4.helpers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class DAO<T extends IHasPrimaryKey> {
	
	private Class<T> clazz;
	
	@PersistenceContext
	EntityManager em;
	
	public DAO(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public T findOne(Object id) {
		return em.find(clazz, id);
	}
	
	//Note: due to how transactions are handled with JTA, it is impossible for .find to get null, and then for .persist to claim an entity already exists. 
	/** persists an entity in the database, returns true on success, false if entity already exists */
	public boolean create(T entity) {
		if (findOne(entity.getPrimaryKey()) != null) return false;
		em.persist(entity);
		return true;
	}
	
	public List<T> findAll() {
		return em.createQuery("SELECT a FROM "+clazz.getName()+" a", clazz).getResultList();	//full class name (.getName()) does indeed work.
	}

}
