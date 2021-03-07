package com.sklep.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sklep.entities.GrupyTowarow;
import com.sklep.entities.Towar;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class TowarDAO {
	private final static String UNIT_NAME = "sklep-simplePU";

	@EJB
	GrupyTowarowDAO grupyTowarowDAO;
	
	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Towar towar) {
		em.persist(towar);
	}

	public Towar merge(Towar towar) {
		return em.merge(towar);
	}

	public void remove(Towar towar) {
		em.remove(em.merge(towar));
	}

	public Towar find(Object id) {
		return em.find(Towar.class, id);
	}
	
	public List<Towar> getFullList() {
		List<Towar> list = null;

		Query query = em.createQuery("select p from Towar p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Towar> getList(Map<String, Object> searchParams) {
		List<Towar> list = null;

		// 1. Build query string with parameters
		String from = "from Towar t ";
		String where = "";
		String orderby = "order by t.idtowar asc";

		// search for producent
		String fraza = (String) searchParams.get("fraza");
		if (fraza != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "t.producent like :fraza ";
		}

		// 2. Create query object
		Query query = em.createQuery(from + where + orderby);

		// 3. Set configured parameters
		if (fraza != null) {
			query.setParameter("fraza", "%"+fraza+"%");
		}

		// 4. Execute query and retrieve list of Towar objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (list.isEmpty()) {
			where = "where t.model like :fraza ";
			
			query = em.createQuery(from + where + orderby);
			
			query.setParameter("fraza", "%"+fraza+"%");
			
			try {
				list = query.getResultList();
				
				System.out.println(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public Towar getTowarDetails(int idTowar){
		
		Towar t = em.find(Towar.class, idTowar);	
		t.getWartoscParametrows().size();
		
		return t;
	}
	
	public Towar stworzTowar(String p, String m, GrupyTowarow g) {
		Towar t = new Towar();
		
		try {
			
			t.setProducent(p);
			t.setModel(m);
			t.setGrupyTowarow(g);
			create(t);
			
		} catch (Exception e) {
			t = null;
		}
		return t;
	}
	
	public void deleteTowarById(int id) {
		
		try {
			Query query = em.createQuery("delete from Towar t where t.idtowar=:idTowar");
			query.setParameter("idTowar", id);
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
