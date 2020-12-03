package jsf.sklep.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jsf.sklep.entities.Zamowienie;

@Stateless
public class ZamowienieDao {
	
	@PersistenceContext
	EntityManager em;
	
	public void insert(Zamowienie zamowienie) {
		em.persist(zamowienie);
	}

	public Zamowienie update(Zamowienie zamowienie) {
		return em.merge(zamowienie);
	}

	public void delete(Zamowienie zamowienie) {
		em.remove(em.merge(zamowienie));
	}

	public Zamowienie get(Object id) {
		return em.find(Zamowienie.class, id);
	}
}
