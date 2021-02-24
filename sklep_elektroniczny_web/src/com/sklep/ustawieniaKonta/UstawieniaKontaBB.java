package com.sklep.ustawieniaKonta;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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
	
	@Inject
	FacesContext context;
	
	private int idKonto;
	private String nowyEmail;
	private String staryEmail;
	private String noweHaslo;
	private String stareHaslo;
	
	Konto k = null;

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
	
	public String getNoweHaslo() {
		return noweHaslo;
	}

	public void setNoweHaslo(String noweHaslo) {
		this.noweHaslo = noweHaslo;
	}

	public String getStareHaslo() {
		return stareHaslo;
	}

	public void setStareHaslo(String stareHaslo) {
		this.stareHaslo = stareHaslo;
	}
	
	public int getIdKontoFromSession() {
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		HttpSession session = (HttpSession) request.getSession(true);
		int idK = (int)session.getAttribute("idKonto");
		
		return idK;
	}
	
	public void zmienEmail() {
		
		idKonto = getIdKontoFromSession();
		
		k = kontoDAO.getKontoFromId(idKonto);
		
		if(k.getEmail().equals(staryEmail)) {
		
			k = kontoDAO.ustawEmail(k, nowyEmail);
		
				if(k != null) {
					context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Pomyœlnie zapisano nowy E-mail", null));
				} else {
					context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wyst¹pi³ b³¹d podczas zapisu", null));
				}
		
		} else {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Stary E-mail nie jest poprawny", null));
		}
	}
	
	public void zmienHaslo() {
		
		idKonto = getIdKontoFromSession();
		
		k = kontoDAO.getKontoFromId(idKonto);
		
		if(k.getHaslo().equals(stareHaslo)) {
			
			k = kontoDAO.ustawHaslo(k, noweHaslo);
			
				if(k != null) {
					context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Pomyœlnie zapisano nowe has³o", null));
				} else {
					context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wyst¹pi³ b³¹d podczas zapisu", null));
				}
	
		} else {
			context.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Stare has³o nie jest poprawne", null));
		}
			
	}
	
	
	public String doUstawien() {
		return PAGE_SETTINGS;
	}

}
