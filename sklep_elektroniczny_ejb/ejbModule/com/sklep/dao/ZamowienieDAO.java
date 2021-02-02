package com.sklep.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sklep.entities.Konto;
import com.sklep.entities.Zamowienie;

@Stateless
public class ZamowienieDAO {
	private final static String UNIT_NAME = "sklep-simplePU";
	
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void insert(Zamowienie zamowienie) {
		em.persist(zamowienie);
	}

	public Zamowienie update(Zamowienie zamowienie) {
		return em.merge(zamowienie);
	}

	public void delete(Zamowienie zamowienie) {
		em.remove(em.merge(zamowienie));
	}

	public Zamowienie get(Object id) {
		return em.find(Zamowienie.class, id);
	}
	
	public void createZamowienie(Integer cena, Konto idKonto) {
		
		Zamowienie z = new Zamowienie();
		
		z.setStatus(1);
		z.setKoszt(cena.toString());
		z.setKonto(idKonto);
		
		insert(z);
	}
	
	public int getSpecificIdZamowienie(Konto konto) {
		int idZamowienie;
		
		try {
			
			Query query = em.createQuery("from Zamowienie z where z.konto_idkonto=:idKonto");
			
			query.setParameter("idKonto", konto);
			
			idZamowienie = (int)query.getSingleResult();
			
			return idZamowienie;
		} catch (javax.persistence.NoResultException e) {
			idZamowienie = 0;
			return idZamowienie;
		}
		
		
	}
}
