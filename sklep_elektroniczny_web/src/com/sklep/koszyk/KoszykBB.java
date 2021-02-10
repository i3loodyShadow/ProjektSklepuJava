package com.sklep.koszyk;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import com.sklep.entities.Towar;
import com.sklep.entities.Zamowienie;

public class KoszykBB {
	
	private Zamowienie zamowienie = new Zamowienie();
	private Zamowienie loaded = new Zamowienie();
	private String prod;
	private String mod;
	private int idKonto;
	private List<Zamowienie> list;
	
	@Inject
	Flash flash;
	
	@Inject
	FacesContext context;

	public String getProd() {
		return prod;
	}

	public void setProd(String prod) {
		this.prod = prod;
	}

	public String getMod() {
		return mod;
	}

	public void setMod(String mod) {
		this.mod = mod;
	}

	public int getIdKonto() {
		return idKonto;
	}

	public void setIdKonto(int idKonto) {
		this.idKonto = idKonto;
	}
	public List<Zamowienie> getList() {
		return list;
	}

	public void setList(List<Zamowienie> list) {
		this.list = list;
	}
	
	public String onLoad() {
		String onLoad = "1";
	
/*		if (loaded != null) {
			towar = loaded;
			
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³êdne u¿ycie systemu!", null));
		}
*/		return onLoad;
	}




}
