package com.sklep.towar;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.model.LazyDataModel;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;

import com.sklep.dao.KontoDAO;
import com.sklep.dao.TowarDAO;
import com.sklep.dao.TowarZamowieniaDAO;
import com.sklep.dao.WartoscParametrowDAO;
import com.sklep.dao.ZamowienieDAO;
import com.sklep.entities.Konto;
import com.sklep.entities.Towar;
import com.sklep.entities.WartoscParametrow;
import com.sklep.entities.WartoscParametrowPK;
import com.sklep.entities.Zamowienie;
import com.sklep.lazy.LazyTowarDataModel;

@Named
@ViewScoped
public class TowarListBB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static final String PAGE_SZCZEG = "/public/szczegTowaru?faces-redirect=true";
	private static final String PAGE_SHOP = "/public/towarList?faces-redirect=true";
	private static final String PAGE_LOGIN = "login?faces-redirect=true";
	private static final String PAGE_EDIT = "/pages/admin/edycjaTowaru?faces-redirect=true";

	private String fraza;
	private List<WartoscParametrow> list;
	private LazyDataModel<Towar> lazyModel;
	
	@Inject
	Flash flash;
	
	@Inject
	@ManagedProperty("#{txtMsg}")
	private ResourceBundle txtMsg;
	
	@EJB
	TowarDAO towarDAO;
	
	@EJB
	KontoDAO kontoDAO;
	
	@EJB
	ZamowienieDAO zamowienieDAO;
	
	@EJB
	WartoscParametrowDAO wartoscParametrowDAO;
	
	@EJB
	TowarZamowieniaDAO towarZamowieniaDAO;
	
	@PostConstruct
	public void init() {
		lazyModel = new LazyTowarDataModel(getFullList());
	}
	
	public LazyDataModel<Towar> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Towar> lazyModel) {
		this.lazyModel = lazyModel;
	}
		
	public String getFraza() {
		return fraza;
	}

	public void setFraza(String fraza) {
		this.fraza = fraza;
	}

	public List<Towar> getFullList(){
		List<Towar> listT = (List<Towar>) flash.get("lista");
		
		if(listT == null){
			return towarDAO.getFullList();
		} else {
			return listT;
		}
	}

	public String getList(){
		List<Towar> list = null;
			
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
			
		if (fraza != null && fraza.length() > 0){
			searchParams.put("fraza", fraza);
		}
			
		//2. Get list
		list = towarDAO.getList(searchParams);
		
		flash.put("lista", list);
			
		return PAGE_SHOP;	
	}
	
	public String login() {
		return PAGE_LOGIN;
	}

	public String szczegTowar(Towar towar){
		flash.put("towar", towar);
		
		return PAGE_SZCZEG;
	}
	
	public int getIdKontoFromSession() {
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		HttpSession session = (HttpSession) request.getSession(true);
		int idK = (int)session.getAttribute("idKonto");
		
		return idK;
	}
	
	public void doKoszyka(Towar towar) {

		int idKonto = getIdKontoFromSession();
		int idTowar = towar.getIdtowar();
		String producent = towar.getProducent();
		String model = towar.getModel();
		Konto k = kontoDAO.find(idKonto);
		list = towarDAO.getTowarDetails(idTowar).getWartoscParametrows();
		
		for (int i = 0; i<list.size(); i++) {
			
			WartoscParametrow test = list.get(i);
			
			if(test.getNazwaParametrow().getNazwaParametru().equals("Cena")) {
				
				String cena = test.getWartoscParametrow();
				Zamowienie z = zamowienieDAO.createZamowienie(cena,k);
				towarZamowieniaDAO.createKoszyk(cena, producent, model, z);
			}
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, txtMsg.getString("info"), txtMsg.getString("addToSCSucc")));
	}
	
	public String usunProdukt(Towar towar) {
		int idKonto = getIdKontoFromSession();
		Konto k = kontoDAO.find(idKonto);
		
		if(k.getRola().getNazwaRoli().equals("admin")) {
			
			List<WartoscParametrow> list = towar.getWartoscParametrows();
			
			for(int i=0; i<list.size(); i++) {
				
				WartoscParametrowPK wpPK = list.get(i).getId();
				WartoscParametrow wp = wartoscParametrowDAO.find(wpPK);
				
				wartoscParametrowDAO.remove(wp);	
			}
			towarDAO.deleteTowarById(towar.getIdtowar());
		}
		return PAGE_SHOP;
	}
	
	public String edytujTowar(Towar towar) {
			flash.put("towar", towar);

		return PAGE_EDIT;
	}
	
	public String doSklepu() {
		return PAGE_SHOP;
	}
}
