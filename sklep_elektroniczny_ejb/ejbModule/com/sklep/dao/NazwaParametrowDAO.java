package com.sklep.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sklep.entities.NazwaParametrow;

@Stateless
public class NazwaParametrowDAO {

	@PersistenceContext
	EntityManager em;
	
	public void insert(NazwaParametrow nazwaParametrow) {
		em.persist(nazwaParametrow);
	}

	public NazwaParametrow update(NazwaParametrow nazwaParametrow) {
		return em.merge(nazwaParametrow);
	}

	public void delete(NazwaParametrow nazwaParametrow) {
		em.remove(em.merge(nazwaParametrow));
	}

	public NazwaParametrow get(Object id) {
		return em.find(NazwaParametrow.class, id);
	}
	
	
}
