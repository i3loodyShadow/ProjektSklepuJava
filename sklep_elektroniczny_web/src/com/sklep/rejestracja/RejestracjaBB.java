package com.sklep.rejestracja;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import com.sklep.dao.KontoDAO;
import com.sklep.entities.Konto;


@Named
@RequestScoped
public class RejestracjaBB {
	
	private static final String PAGE_REGISTER = "/public/rejestracja?faces-redirect=true";
	private static final String PAGE_LOGIN = "/public/login?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private String login;
	private String haslo;
	private String email;
	
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getHaslo() {
		return haslo;
	}
	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@EJB
	KontoDAO kontoDAO;
	public String zarejestruj() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		
		Konto loginUnique = kontoDAO.isUserUnique(login);
		
		if(loginUnique != null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Login jest zajêty, proszê spróbowaæ wpisaæ inny login", null));
			return PAGE_STAY_AT_THE_SAME;	
		}	
			boolean noweKonto = kontoDAO.createKonto(email, login, haslo);
		if(noweKonto == true) {
			return PAGE_LOGIN;
		} else {
			return PAGE_STAY_AT_THE_SAME;
		}
	} 
	
	public String doRejestracji() {
		return PAGE_REGISTER;
	}
	
}
