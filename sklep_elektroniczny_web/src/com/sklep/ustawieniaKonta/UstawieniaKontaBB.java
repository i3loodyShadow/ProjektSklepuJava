package com.sklep.ustawieniaKonta;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sklep.dao.KontoDAO;
import com.sklep.dao.TowarZamowieniaDAO;
import com.sklep.dao.ZamowienieDAO;
import com.sklep.entities.Konto;
import com.sklep.entities.TowarZamowienia;
import com.sklep.entities.Zamowienie;

@Named
@RequestScoped
public class UstawieniaKontaBB {
	
	private static final String PAGE_SETTINGS = "/public/ustawieniaKonta?faces-redirect=true";
	private static final String PAGE_LOGIN = "/public/login";
	
	@EJB
	KontoDAO kontoDAO;
	
	@EJB
	ZamowienieDAO zamowienieDAO;
	
	@EJB
	TowarZamowieniaDAO towarZamowieniaDAO;
	
	@Inject
	FacesContext context;
	
	private int idKonto;
	private String nowyEmail;
	private String nowyEmailPowt;
	private String staryEmail;
	private String noweHaslo;
	private String stareHaslo;
	
	Konto k = null;
	Zamowienie z;
	private List<Zamowienie> list;
	private List<Integer> listIdZ = new ArrayList<Integer>();
	private List<TowarZamowienia> tz = new ArrayList<TowarZamowienia>();

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
	
	public String getNowyEmailPowt() {
		return nowyEmailPowt;
	}

	public void setNowyEmailPowt(String nowyEmailPowt) {
		this.nowyEmailPowt = nowyEmailPowt;
	}
	
	public List<Zamowienie> getList() {
		return list;
	}

	public void setList(List<Zamowienie> list) {
		this.list = list;
	}
	
	public List<Integer> getListIdZ() {
		return listIdZ;
	}

	public void setListIdZ(List<Integer> listIdZ) {
		this.listIdZ = listIdZ;
	}
	
	public List<TowarZamowienia> getTz() {
		return tz;
	}

	public void setTz(List<TowarZamowienia> tz) {
		this.tz = tz;
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
		
		String sE = k.getEmail();
		
		if(sE.equals(staryEmail) && nowyEmail.equals(nowyEmailPowt)) {
		
			k = kontoDAO.ustawEmail(k, nowyEmail);
		
				if(k != null) {
					context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Pomy�lnie zapisano nowy E-mail", null));
				} else {
					context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wyst�pi� b��d podczas zapisu!", null));
				}
		
		} 
		
		if(!sE.equals(staryEmail)){
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Stary E-mail nie jest poprawny!", null));	
		}
		
		if(!nowyEmail.equals(nowyEmailPowt)){
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wprowadzone adresy E-mail nie s� sobie r�wne!", null));
			
		}
	}
	
	public void zmienHaslo() {
		
		idKonto = getIdKontoFromSession();
		
		k = kontoDAO.getKontoFromId(idKonto);
		
		if(k.getHaslo().equals(stareHaslo)) {
			
			k = kontoDAO.ustawHaslo(k, noweHaslo);
			
				if(k != null) {
					context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Pomy�lnie zapisano nowe has�o", null));
				} else {
					context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wyst�pi� b��d podczas zapisu", null));
				}
	
		} else {
			context.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Stare has�o nie jest poprawne", null));
		}
			
	}
	
	public String usunKonto() {
		
		idKonto = getIdKontoFromSession();
		
		k = kontoDAO.getZamowienieDetails(idKonto);
		
		list = k.getZamowienies();
		
		if(list.isEmpty()) {
			
			Logout();
			
			kontoDAO.remove(k);
			
		} else {
			
			for(int i=0; i<list.size(); i++) {
				
				Zamowienie z = list.get(i);
				
				listIdZ.add(z.getIdzamowienie());
				
			}
			
			for (int i = 0; i<listIdZ.size(); i++){
				
				tz.addAll(zamowienieDAO.getTowarZamowieniaDetails(listIdZ.get(i)).getTowarZamowienias());
				
			}
			
			for (int i=0; i<tz.size(); i++) {
					
				int idTZ = tz.get(i).getIdtowarZamowienia();
				
				TowarZamowienia towZam = towarZamowieniaDAO.get(idTZ);
				
				int idZ = towZam.getZamowienie().getIdzamowienie();
				
				towarZamowieniaDAO.delete(towZam);
				
				zamowienieDAO.deleteZamowienieById(idZ);
				
			}
			
			Logout();
			
			kontoDAO.deleteKontoById(idKonto);

		}
		return PAGE_LOGIN;
	}
	
	public void Logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);

		session.invalidate();
	}
	
	public String doUstawien() {
		return PAGE_SETTINGS;
	}

}
