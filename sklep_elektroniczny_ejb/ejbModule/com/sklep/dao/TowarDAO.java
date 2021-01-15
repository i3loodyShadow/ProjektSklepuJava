package com.sklep.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sklep.entities.Towar;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class TowarDAO {
	private final static String UNIT_NAME = "sklep-simplePU";

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
		String select = "select p ";
		String from = "from Towar p ";
		String where = "";
		String orderby = "order by p.idtowar asc, p.producent";

		// search for producent
		String producent = (String) searchParams.get("producent");
		if (producent != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.producent like :producent ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (producent != null) {
			query.setParameter("producent", producent+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Towar objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Towar> getTowarDetails(int searchParam){
		
		List<Towar> list = null;
		String select = "select idtowar, wartosc_parametrow, nazwa_parametru ";
		String from = "from Towar t ";
		String join1 = "join t.wartoscParametrows w ";
		String join2 = "join w.nazwaParametrow n ";
		String where = "where idtowar like :idTowar ";
		
		String idTow = Integer.toString(searchParam);
		
		Query query = em.createQuery(select + from + join1 + join2 + where);

		
		query.setParameter("idTowar", idTow);
		
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
