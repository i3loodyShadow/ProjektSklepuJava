package com.sklep.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sklep.entities.TowarZamowienia;

@Stateless
public class TowarZamowieniaDAO {
	private final static String UNIT_NAME = "sklep-simplePU";
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void insert(TowarZamowienia towarZamowienia) {
		em.persist(towarZamowienia);
	}

	public TowarZamowienia update(TowarZamowienia towarZamowienia) {
		return em.merge(towarZamowienia);
	}

	public void delete(TowarZamowienia towarZamowienia) {
		em.remove(em.merge(towarZamowienia));
	}

	public TowarZamowienia get(Object id) {
		return em.find(TowarZamowienia.class, id);
	}
	
	public void createKoszyk(Integer cena, String producent, String model, int idZamowienie) {
		
		TowarZamowienia t = new TowarZamowienia();
		
		t.setIdtowarZamowienia(idZamowienie);
		t.setProducent(producent);
		t.setModel(model);
		t.setCena(cena.toString());
		
		insert(t);
	}
}
