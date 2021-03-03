package com.sklep.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sklep.entities.TowarZamowienia;
import com.sklep.entities.Zamowienie;

@Stateless
public class TowarZamowieniaDAO {
	private final static String UNIT_NAME = "sklep-simplePU";
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(TowarZamowienia towarZamowienia) {
		em.persist(towarZamowienia);
	}

	public TowarZamowienia merge(TowarZamowienia towarZamowienia) {
		return em.merge(towarZamowienia);
	}

	public void remove(TowarZamowienia towarZamowienia) {
		em.remove(em.merge(towarZamowienia));
	}

	public TowarZamowienia find(Object id) {
		return em.find(TowarZamowienia.class, id);
	}
	
	public TowarZamowienia createKoszyk(String cena, String producent, String model, Zamowienie z) {
		
		TowarZamowienia t = new TowarZamowienia();
		
		t.setZamowienie(z);
		t.setProducent(producent);
		t.setModel(model);
		t.setCena(cena);
		
		merge(t);
		
		return t;
	}
	
}
