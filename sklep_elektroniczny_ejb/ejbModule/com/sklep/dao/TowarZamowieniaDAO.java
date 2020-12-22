package com.sklep.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sklep.entities.TowarZamowienia;

@Stateless

public class TowarZamowieniaDAO {
	
	@PersistenceContext
	EntityManager em;
	
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

}
