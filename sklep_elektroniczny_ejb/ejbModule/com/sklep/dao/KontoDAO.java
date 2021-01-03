package com.sklep.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sklep.entities.Konto;

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
	
	
/*	public List<Konto> getList(Map<String, Object> searchParams) {
		List<Konto> list = null;

		// 1. Build query string with parameters
		String select = "select Idkonto k";
		String from = "from Konto k ";
		String where = "";

		// search for producent
		String loginQuerry = (String) searchParams.get("login");
		if (loginQuerry != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.producent like :producent ";
		}

		// 2. Create query object
		Query query = em.createQuery(select + from + where);

		// 3. Set configured parameters
		if (loginQuerry != null) {
			query.setParameter("login", loginQuerry);
		}

		// 4. Execute query and retrieve list of Konto objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	*/
	// simulate finding user in DB
	public Konto getUser(String login, String haslo) {
		
		Konto u = null;
		
		Konto t = new Konto();
			t.setLogin(login);
			t.getIdkonto();
		

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
		if (konto.getLogin().equals("user3")) {
			roles.add("admin");
		}
		
		return roles;	
	}
}
