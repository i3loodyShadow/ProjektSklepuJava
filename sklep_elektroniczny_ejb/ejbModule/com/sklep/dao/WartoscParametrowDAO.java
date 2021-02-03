package com.sklep.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sklep.entities.WartoscParametrow;

@Stateless
public class WartoscParametrowDAO {
	private final static String UNIT_NAME = "sklep-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void insert(WartoscParametrow wartoscParametrow) {
		em.persist(wartoscParametrow);
	}

	public WartoscParametrow update(WartoscParametrow wartoscParametrow) {
		return em.merge(wartoscParametrow);
	}

	public void delete(WartoscParametrow wartoscParametrow) {
		em.remove(em.merge(wartoscParametrow));
	}

	public WartoscParametrow get(Object id) {
		return em.find(WartoscParametrow.class, id);
	}
	
	public Integer getCena(int idTowar) {
		Integer cena;
		
		try {
			
			Query query = em.createQuery("from WartoscParametrow w where w.towar_idtowar=:idTowar and w.nazwa_parametrow_idnazwa_parametrow=:trzy");
			
			query.setParameter("idTowar", idTowar);
			
			int trzy = 3;
			query.setParameter("trzy", trzy);
			
			cena = (Integer)query.getSingleResult();
			
			return cena;
		} catch (javax.persistence.NoResultException e){
			cena = 0;
			return cena;
		} catch (java.lang.NullPointerException e) {
			cena = 0;
			return cena;
		}
			
	}
}
