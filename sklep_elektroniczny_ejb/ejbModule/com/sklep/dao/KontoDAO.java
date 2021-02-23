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

	@PersistenceContext(unitName = UNIT_NAME)
	EntityManager em;
	
	public void create(Konto konto) {
		em.persist(konto);
	}

	public Konto merge(Konto konto) {
		return em.merge(konto);
	}

	public void remove(Konto konto) {
		em.remove(em.merge(konto));
	}

	public Konto find(Object id) {
		return em.find(Konto.class, id);
	}
	
	public Konto getKonto(String login, String haslo) {
		
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
	
	public Konto isUserUnique(String login) {
		
		try {
			
			Query query = em.createQuery("from Konto k where k.login=:login");
			
			query.setParameter("login", login);
			
			Konto k = (Konto)query.getSingleResult();
			
			return k;
		} catch (javax.persistence.NoResultException e) {
			Konto k = null;
			
			return k;
		}
		
	}
	
	public boolean createKonto(String email, String login, String haslo) {
		
		boolean success;
		
		try {
			Konto k = new Konto();
			Rola r = new Rola();
		
			r.setIdrola(2);
			r.setNazwaRoli("user");
			
			k.setEmail(email);
			k.setLogin(login);
			k.setHaslo(haslo);
			k.setRola(r);
			
			create(k);
			
			success = true;
			return success;
		} catch (Exception e){
			success = false;
			return success;
		}
	}
	
	public Konto getKontoFromId(int idKonto) {
		
		Konto k = new Konto();
		
		try {
			
			Query query = em.createQuery("from Konto k where k.idkonto=:idKonto");
			
			query.setParameter("idKonto", idKonto);
			
			k = (Konto)query.getSingleResult();
			return k;

		} catch (javax.persistence.NoResultException e) {
			k = null;
			
			return k;
		}
	}
	
	public Konto getZamowienieDetails(int idKonto){
		
		Konto k = em.find(Konto.class, idKonto);	
		k.getZamowienies().size();
		
		return k;
	}
	
	public Konto ustawEmail(Konto k, String nowyEmail) {

		k.setEmail(nowyEmail);
		merge(k);
		
		return k;
	}
}
