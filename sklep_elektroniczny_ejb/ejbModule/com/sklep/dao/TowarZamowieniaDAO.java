package com.sklep.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sklep.entities.Towar;
import com.sklep.entities.TowarZamowienia;
import com.sklep.entities.Zamowienie;

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
	
	public TowarZamowienia createKoszyk(String cena, String producent, String model, Zamowienie z) {
		
		TowarZamowienia t = new TowarZamowienia();
		
		t.setZamowienie(z);
		t.setProducent(producent);
		t.setModel(model);
		t.setCena(cena);
		
		update(t);
		
		return t;
	}
	
}
