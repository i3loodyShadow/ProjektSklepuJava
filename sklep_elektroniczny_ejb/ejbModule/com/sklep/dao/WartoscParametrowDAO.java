package com.sklep.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sklep.entities.WartoscParametrow;
import com.sklep.entities.WartoscParametrowPK;

@Stateless
public class WartoscParametrowDAO {
	private final static String UNIT_NAME = "sklep-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(WartoscParametrow wartoscParametrow) {
		em.persist(wartoscParametrow);
	}

	public WartoscParametrow merge(WartoscParametrow wartoscParametrow) {
		return em.merge(wartoscParametrow);
	}

	public void remove(WartoscParametrow wartoscParametrow) {
		em.remove(em.merge(wartoscParametrow));
	}

	public WartoscParametrow find(Object id) {
		return em.find(WartoscParametrow.class, id);
	}
	
	public int getMaxWPK() {
		try {
			
			List<WartoscParametrow> wp = null;
			int id = 0;
			Query query = em.createQuery("select w from WartoscParametrow w");
			
			wp = query.getResultList();
			
			for(int i=0; i<wp.size(); i++) {
				
				if(i == wp.size()-1) {
					id = wp.get(i).getId().getIdwartoscParametrow();
				}
			}
			
			id++;
			return id;
		} catch (javax.persistence.NoResultException e) {
			int id = 0;
			return id;
		}
	}
}
