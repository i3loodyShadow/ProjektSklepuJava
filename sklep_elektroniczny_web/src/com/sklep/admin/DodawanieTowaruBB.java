package com.sklep.admin;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.sklep.dao.GrupyTowarowDAO;
import com.sklep.dao.TowarDAO;
import com.sklep.entities.GrupyTowarow;
import com.sklep.entities.NazwaParametrow;
import com.sklep.entities.Towar;

@Named
@RequestScoped
public class DodawanieTowaruBB {
	
	private static final String DODAJ_TOWAR = "/pages/admin/dodawanieTowaru?faces-redirect=true";

	private String producent;
	private String model;
	private String wybranaGrupaTowarow;
	
	private List<NazwaParametrow> list;
	
	@EJB
	TowarDAO towarDAO;
	
	@EJB
	GrupyTowarowDAO grupyTowarowDAO;
	
	public String getProducent() {
		return producent;
	}
	
	public void setProducent(String producent) {
		this.producent = producent;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public String getWybranaGrupaTowarow() {
		return wybranaGrupaTowarow;
	}

	public void setWybranaGrupaTowarow(String wybranaGrupaTowarow) {
		this.wybranaGrupaTowarow = wybranaGrupaTowarow;
	}
	
	public List<NazwaParametrow> getList() {
		list = grupyTowarowDAO.getNPList();
		return list;
	}

	public void setList(List<NazwaParametrow> list) {
		this.list = list;
	}
	
	public String wyswietlStroneDodawania() {
		return DODAJ_TOWAR;
	}
	
	public void dodajProdukt() {
				
		GrupyTowarow g = grupyTowarowDAO.getGTByName(wybranaGrupaTowarow);
		Towar t = towarDAO.stworzTowar(producent, model, g);
		
		if(t != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacja", "Pomyœlnie dodano produkt"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d", "Wyst¹pi³ b³¹d podczas dodawania produktu!"));
		}
		
	}

}
