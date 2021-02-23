package com.sklep.ustawieniaKonta;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sklep.dao.KontoDAO;
import com.sklep.entities.Konto;

@Named
@RequestScoped
public class UstawieniaKontaBB {
	
	private static final String PAGE_SETTINGS = "/public/ustawieniaKonta?faces-redirect=true";
	
	@EJB
	KontoDAO kontoDAO;
	
	private String nowyEmail;
	private String staryEmail;

	public String getNowyEmail() {
		return nowyEmail;
	}


	public void setNowyEmail(String nowyEmail) {
		this.nowyEmail = nowyEmail;
	}


	public String getStaryEmail() {
		return staryEmail;
	}


	public void setStaryEmail(String staryEmail) {
		this.staryEmail = staryEmail;
	}
	
	public int getIdKontoFromSession() {
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		HttpSession session = (HttpSession) request.getSession(true);
		int idK = (int)session.getAttribute("idKonto");
		
		return idK;
	}
	
	public void zmienEmail() {
		
		int idKonto = getIdKontoFromSession();
		
		Konto k = kontoDAO.getKontoFromId(idKonto);
		
		k = kontoDAO.ustawEmail(k, nowyEmail);
		
		if(k != null) {
			//tutaj bêdzie wygenerowana informacja je¿eli pomyœlne zmienienie emaila
		} else {
			//a tutaj je¿eli coœ siê nie powiedzie
		}
	}
	
	public String doUstawien() {
		return PAGE_SETTINGS;
	}

}
