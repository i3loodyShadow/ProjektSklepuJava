package com.sklep.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sklep.entities.Konto;
import com.sklep.entities.Rola;

@Stateless
public class KontoDAO {	
	private final static String UNIT_NAME = "sklep-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	EntityManager em;
	
	public void insert(Konto konto) {
		em.persist(konto);
	}

	public Konto update(Konto konto) {
		return em.merge(konto);
	}

	public void delete(Konto konto) {
		em.remove(em.merge(konto));
	}

	public Konto get(Object id) {
		return em.find(Konto.class, id);
	}
	
	public Konto getKonto (String login, String haslo) {
		
		try {
		
			Query query = em.createQuery("from Konto k where k.login=:login and k.haslo=:haslo");
		
			query.setParameter("login",login);
			query.setParameter("haslo", haslo);
		
			Konto k = (Konto)query.getSingleResult();
	
			return k;
		} catch (javax.persistence.NoResultException e) {
			Konto k = null;
				
			return k;
		}
	}

	// simulate retrieving roles of a User from DB
	public List<String> getKontoRole(Konto konto) {
		
		ArrayList<String> role = new ArrayList<String>();
		
		Integer rolaI = konto.getRola().getIdrola();
		
		if (rolaI == 1) {
			role.add("admin");
		}
		if (rolaI == 2) {
			role.add("user");
		}
		
		return role;	
	}
}
