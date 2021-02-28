package com.sklep.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sklep.entities.NazwaParametrow;

@Stateless
public class NazwaParametrowDAO {
	private final static String UNIT_NAME = "sklep-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
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
	
	public NazwaParametrow getObjectByName(String name) {
		try {
			
			Query query = em.createQuery("from NazwaParametrow n where n.nazwaParametru=:name");
			
			query.setParameter("name", name);
			
			NazwaParametrow n = (NazwaParametrow)query.getSingleResult();
			
			return n;
		} catch (javax.persistence.NoResultException e) {
			NazwaParametrow n = null;
			return n;
		}
	}
}
