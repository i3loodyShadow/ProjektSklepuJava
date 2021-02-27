package com.sklep.admin;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sklep.dao.TowarDAO;
import com.sklep.entities.GrupyTowarow;
import com.sklep.entities.Towar;

@Named
@ViewScoped
public class EdycjaTowaruBB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final String PAGE_STAY_AT_THE_SAME = null;
	private static final String PAGE_TOWAR_LIST = "/public/towarList?faces-redirect=true";
	
	@EJB
	TowarDAO towarDAO;
	
	@Inject
	FacesContext context;
	
	@Inject
	Flash flash;

	private String wybranaNP;
	private Towar towar = new Towar();
	private Towar loaded = null;
	private List<String> list = new ArrayList<String>();
	
	public Towar getTowar() {
		return towar;
	}
	
	public String getWybranaNP() {
		return wybranaNP;
	}

	public void setWybranaNP(String wybranaNP) {
		this.wybranaNP = wybranaNP;
	}
	
	public void onLoad() throws IOException {
		
		loaded = (Towar) flash.get("towar");
		if(loaded != null) {
			towar = loaded;
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³êdne u¿ycie systemu", null));
		}
		
	}
	
	public String zapisz() {
		if(loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}
		
		try {
			int id = towar.getIdtowar();
			Integer iId = Integer.valueOf(id);
			
			if(iId != null) {
				towarDAO.merge(towar);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wyst¹pi³ b³¹d podczas zapisu", null));
			
			return PAGE_STAY_AT_THE_SAME;
		}
		
		return PAGE_TOWAR_LIST;
	}

	public List<String> getList() {
		Towar t = (Towar) flash.get("towar");
		GrupyTowarow GT = t.getGrupyTowarow();
		
		for(int i=0; i<GT.getNazwaParametrows().size(); i++) {
			list.add(GT.getNazwaParametrows().get(i).getNazwaParametru());
			
		}
		
		return list;
	}
	
	
}
