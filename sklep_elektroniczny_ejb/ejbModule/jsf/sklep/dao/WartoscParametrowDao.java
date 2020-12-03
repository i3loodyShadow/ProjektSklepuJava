package jsf.sklep.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jsf.sklep.entities.WartoscParametrow;

@Stateless

public class WartoscParametrowDao {

	@PersistenceContext
	EntityManager em;
	
	public void insert(WartoscParametrow wartoscParametrow) {
		em.persist(wartoscParametrow);
	}

	public WartoscParametrow update(WartoscParametrow wartoscParametrow) {
		return em.merge(wartoscParametrow);
	}

	public void delete(WartoscParametrow wartoscParametrow) {
		em.remove(em.merge(wartoscParametrow));
	}

	public WartoscParametrow get(Object id) {
		return em.find(WartoscParametrow.class, id);
	}
}
