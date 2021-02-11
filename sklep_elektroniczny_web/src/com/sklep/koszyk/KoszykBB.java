package com.sklep.koszyk;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sklep.dao.KontoDAO;
import com.sklep.dao.ZamowienieDAO;
import com.sklep.entities.TowarZamowienia;
import com.sklep.entities.Zamowienie;

@Named
@RequestScoped
public class KoszykBB {
	private static final String PAGE_KOSZYK = "/public/koszyk?faces-redirect=true";
	
	private String prod;
	private String mod;
	private int idKonto;
	private List<Zamowienie> list;
	private List<Integer> listIdZ = new ArrayList<Integer>();
	private List<TowarZamowienia> tz = new ArrayList<TowarZamowienia>();
	
	@Inject
	Flash flash;
	
	@Inject
	FacesContext context;
	
	@EJB
	KontoDAO kontoDAO;
	
	@EJB
	ZamowienieDAO zamowienieDAO;

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
	
	public void onLoad() {
		
		idKonto = getIdKontoFromSession();
		
		list = kontoDAO.getZamowienieDetails(idKonto).getZamowienies();
		
		for (int i = 0; i<list.size(); i++) {
			
			Zamowienie z = list.get(i);
			
			listIdZ.add(z.getIdzamowienie());
				
		}
		
		for (int i = 0; i<listIdZ.size(); i++){
			
			tz.addAll(zamowienieDAO.getTowarZamowieniaDetails(listIdZ.get(i)).getTowarZamowienias());
			
		}

	}
	
	

	public String doKoszyka() {
		return PAGE_KOSZYK;
	}

}
