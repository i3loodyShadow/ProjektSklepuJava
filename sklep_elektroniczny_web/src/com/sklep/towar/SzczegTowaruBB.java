package com.sklep.towar;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.sklep.dao.TowarDAO;
import com.sklep.entities.Towar;

@Named
@ViewScoped
public class SzczegTowaruBB implements Serializable{
	private static final long serialVersionUID = 1L;

	private static final String PAGE_MAIN = "/public/towarList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private Towar towar = new Towar();
	private Towar loaded = new Towar();
	private List<Towar> list;
	
	@EJB
	TowarDAO TowarDAO;
	
	@Inject
	FacesContext context;

	@Inject
	Flash flash;
	
	public Towar getTowar() {
		return towar;
	}
	
	public List<Towar> getList() {
		return list;
	}

	public void setList(List<Towar> list) {
		this.list = list;
	}
	
	public void onLoad() throws IOException{
		
		loaded = (Towar) flash.get("towar");
	
		if(loaded != null) {
		towar = loaded;
		
		int idTowaru = towar.getIdtowar();
		
		list = TowarDAO.getTowarDetails(idTowaru);
				
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³êdne u¿ycie systemu!", null));
		}
	}
	 
}
