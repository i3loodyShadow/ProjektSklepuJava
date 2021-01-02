package com.sklep.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sklep.entities.Konto;

@Stateless
public class KontoDAO {
	
	@PersistenceContext
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
	
	// simulate finding user in DB
	public Konto getUser(String login, String haslo) {
		
		Konto u = null;

		if (login.equals("user1") && haslo.equals("password")) {
			u = new Konto();
			u.setLogin(login);
			u.setHaslo(haslo);
		}

		if (login.equals("user2") && haslo.equals("password")) {
			u = new Konto();
			u.setLogin(login);
			u.setHaslo(haslo);

		}

		return u;
	}

	// simulate retrieving roles of a User from DB
	public List<String> getUserRoles(Konto konto) {
		
		ArrayList<String> roles = new ArrayList<String>();
		
		if (konto.getLogin().equals("user1")) {
			roles.add("user");
		}
		if (konto.getLogin().equals("user2")) {
			roles.add("manager");
		}
		if (konto.getLogin().equals("user3")) {
			roles.add("admin");
		}
		
		return roles;	
	}
}
