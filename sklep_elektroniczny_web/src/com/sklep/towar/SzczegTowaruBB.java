package com.sklep.towar;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.sklep.dao.TowarDAO;
import com.sklep.dao.NazwaParametrowDAO;
import com.sklep.dao.WartoscParametrowDAO;

import com.sklep.entities.Towar;
import com.sklep.entities.NazwaParametrow;
import com.sklep.entities.WartoscParametrow;

public class SzczegTowaruBB implements Serializable{
	private static final long serialVersionUID = 1L;

	private static final String PAGE_MAIN = "/public/towarList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private Towar towar = new Towar();
	private Towar loaded = new Towar();
	
	@EJB
	TowarDAO TowarDAO;
	
	@Inject
	FacesContext context;

	@Inject
	Flash flash;
	
	public Towar getTowar() {
		return towar;
	}
	
	public void onLoad() throws IOException{
		
		loaded = (Towar) flash.get("towar");
	
		if(loaded != null) {
		towar = loaded;
		
		int idTowaru = towar.getIdtowar();
		
		int idGrTow = TowarDAO.getIdGT(idTowaru);
		
		
		
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B��dne u�ycie systemu!", null));
		}
	}
	 
}
