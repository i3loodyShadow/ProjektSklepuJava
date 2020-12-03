package jsf.sklep.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jsf.sklep.entities.Konto;

@Stateless
public class KontoDao {
	
	@PersistenceContext
	EntityManager em;
	
	public void insert(Konto konto) {
		em.persist(konto);
	}

	public Konto update(Konto konto) {
		return em.merge(konto);
	}

	public void delete(Konto konto) {
		em.remove(em.merge(konto));
	}

	public Konto get(Object id) {
		return em.find(Konto.class, id);
	}
}
