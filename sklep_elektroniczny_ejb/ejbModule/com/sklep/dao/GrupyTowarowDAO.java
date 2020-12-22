package com.sklep.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sklep.entities.GrupyTowarow;

@Stateless
public class GrupyTowarowDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public void insert(GrupyTowarow grupyTowarow) {
		em.persist(grupyTowarow);
	}

	public GrupyTowarow update(GrupyTowarow grupyTowarow) {
		return em.merge(grupyTowarow);
	}

	public void delete(GrupyTowarow grupyTowarow) {
		em.remove(em.merge(grupyTowarow));
	}

	public GrupyTowarow get(Object id) {
		return em.find(GrupyTowarow.class, id);
	}
}
