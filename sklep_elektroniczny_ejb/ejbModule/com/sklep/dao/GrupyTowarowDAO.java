package com.sklep.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sklep.entities.GrupyTowarow;
import com.sklep.entities.Konto;
import com.sklep.entities.NazwaParametrow;

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
	
	public List<NazwaParametrow> getNPList(){
		
		List<NazwaParametrow> np;
		
		try {
			
			Query query = em.createQuery("select nazwaGrupy from GrupyTowarow g");
			
			np = query.getResultList();
			return np ;

		} catch (Exception e) {
			np = null;
			
			return np;
		}
		
	}
	
	public GrupyTowarow getGTByName(String name) {
		
		GrupyTowarow g = new GrupyTowarow();
		
		try {
			
			Query query = em.createQuery("from GrupyTowarow g where g.nazwaGrupy=:nazwaGrupy");
			
			query.setParameter("nazwaGrupy",name);
			
			g = (GrupyTowarow)query.getSingleResult();
			return g ;

		} catch (Exception e) {
			g = null;
			
			return g;
		}
	}
	
}
