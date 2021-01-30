package com.sklep.towar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.Flash;
import com.sklep.dao.TowarDAO;
import com.sklep.entities.Towar;

@Named
@RequestScoped
public class TowarListBB {
	private static final String PAGE_SZCZEG = "/public/szczegTowaru?faces-redirect=true";
	private static final String PAGE_SHOP = "/public/towarList?faces-redirect=true";
	private static final String PAGE_LOGIN = "login?faces-redirect=true";

	private String producent;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	TowarDAO towarDAO;
		
	public String getProducent() {
		return producent;
	}

	public void setProducent(String producent) {
		this.producent = producent;
	}

	public List<Towar> getFullList(){
		return towarDAO.getFullList();
	}

	public List<Towar> getList(){
		List<Towar> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (producent != null && producent.length() > 0){
			searchParams.put("producent", producent);
		}
		
		//2. Get list
		list = towarDAO.getList(searchParams);
		
		return list;
	}
	
	public String login() {
		
		return PAGE_LOGIN;
	}

	public String szczegTowar(Towar towar){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("towar", towar);
		
		//2. Pass object through flash 
		flash.put("towar", towar);
		
		return PAGE_SZCZEG;
	}
	
	public String doSklepu() {
		return PAGE_SHOP;
	}
}
