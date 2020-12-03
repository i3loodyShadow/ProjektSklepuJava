package jsf.sklep.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jsf.sklep.entities.Towar;

@Stateless

public class TowarDao {
	
	@PersistenceContext
	EntityManager em;
	
	public void insert(Towar towar) {
		em.persist(towar);
	}

	public Towar update(Towar towar) {
		return em.merge(towar);
	}

	public void delete(Towar towar) {
		em.remove(em.merge(towar));
	}

	public Towar get(Object id) {
		return em.find(Towar.class, id);
	}
}
