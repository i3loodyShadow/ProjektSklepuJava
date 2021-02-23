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
	EntityManager em;
	
	public void create(Zamowienie zamowienie) {
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
	
	public Zamowienie createZamowienie(String cena, Konto konto) {
		
		Zamowienie z = new Zamowienie();
		
		z.setStatus(1);
		z.setKoszt(cena);
		z.setKonto(konto);
		
		create(z);
		
		return z;
	}
	
	public Zamowienie getTowarZamowieniaDetails(int idZ){
	
		Zamowienie z = em.find(Zamowienie.class, idZ);	
		z.getTowarZamowienias().size();
		
		return z;
	}
	
	public void deleteZamowienieById(int id) {
		
		Query query = em.createQuery("delete from Zamowienie z where z.idzamowienie=:idZamowienie");
		
		query.setParameter("idZamowienie",id);
		
		query.executeUpdate();
		
	}
}
