package com.sklep.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sklep.entities.GrupyTowarow;
import com.sklep.entities.NazwaParametrow;

@Stateless
public class NazwaParametrowDAO {
	private final static String UNIT_NAME = "sklep-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	EntityManager em;
	
	public void create(NazwaParametrow nazwaParametrow) {
		em.persist(nazwaParametrow);
	}

	public NazwaParametrow merge(NazwaParametrow nazwaParametrow) {
		return em.merge(nazwaParametrow);
	}

	public void remove(NazwaParametrow nazwaParametrow) {
		em.remove(em.merge(nazwaParametrow));
	}

	public NazwaParametrow find(Object id) {
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
	
	public List<String> getNPList(){
		List<String> NPList = null;
		
		try {
			Query query = em.createQuery("select nazwaParametru from NazwaParametrow n");
			NPList = query.getResultList();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return NPList;
	}
	
	public List<GrupyTowarow> pobierzGTDanejNP(String nazwaParametru){
		List<GrupyTowarow> GTDanejNPList = null;
		
		try {
			Query query = em.createQuery("select grupyTowarows from NazwaParametrow n where n.nazwaParametru=:NazwaParametru");
			query.setParameter("NazwaParametru", nazwaParametru);
			GTDanejNPList = query.getResultList();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return GTDanejNPList;
	}
}
