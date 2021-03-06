package com.sklep.admin;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sklep.dao.NazwaParametrowDAO;
import com.sklep.dao.TowarDAO;
import com.sklep.dao.WartoscParametrowDAO;
import com.sklep.entities.GrupyTowarow;
import com.sklep.entities.NazwaParametrow;
import com.sklep.entities.Towar;
import com.sklep.entities.WartoscParametrow;
import com.sklep.entities.WartoscParametrowPK;

@Named
@ViewScoped
public class EdycjaTowaruBB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final String PAGE_STAY_AT_THE_SAME = null;
	private static final String PAGE_TOWAR_LIST = "/public/towarList?faces-redirect=true";
	
	@EJB
	TowarDAO towarDAO;
	
	@EJB
	NazwaParametrowDAO nazwaParametrowDAO;
	
	@EJB
	WartoscParametrowDAO wartoscParametrowDAO;
	
	@Inject
	Flash flash;
	
	@Inject
	@ManagedProperty("#{txtMsg}")
	private ResourceBundle txtMsg;

	private String wybranaNP;
	private String wartoscP;
	private Towar towar = new Towar();
	private List<String> list = new ArrayList<String>();
	private List<NazwaParametrow> listNPObj = new ArrayList<NazwaParametrow>();
	
	public Towar getTowar() {
		return towar;
	}
	
	public void setTowar(Towar towar) {
		this.towar = towar;
	}
	
	public String getWybranaNP() {
		return wybranaNP;
	}

	public void setWybranaNP(String wybranaNP) {
		this.wybranaNP = wybranaNP;
	}
	
	public String getWartoscP() {
		return wartoscP;
	}

	public void setWartoscP(String wartoscP) {
		this.wartoscP = wartoscP;
	}
	
	public List<NazwaParametrow> getListNPObj() {
		return listNPObj;
	}

	public void setListNPObj(List<NazwaParametrow> listNPObj) {
		this.listNPObj = listNPObj;
	}
	
	public void onLoad() throws IOException {
		
		towar = (Towar) flash.get("towar");
		if(towar == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, txtMsg.getString("err"),  txtMsg.getString("wrongSysUsage")));
		}
		
	}
	
	public String zapisz() {
		if(towar == null) {
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, txtMsg.getString("err"), txtMsg.getString("errOccWhileSav")));
			
			return PAGE_STAY_AT_THE_SAME;
		}
		
		return PAGE_TOWAR_LIST;
	}

	public List<String> getList() {
		try {
		GrupyTowarow GT = towar.getGrupyTowarow();
		
			for(int i=0; i<GT.getNazwaParametrows().size(); i++) {
				list.add(GT.getNazwaParametrows().get(i).getNazwaParametru());
				
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, txtMsg.getString("err"), txtMsg.getString("wrongSysUsage")));
		}
		return list;
	}
	
	public void zapiszSzczeg() {
		List <WartoscParametrow> listWP = towar.getWartoscParametrows();
		GrupyTowarow g = towar.getGrupyTowarow();
		List<NazwaParametrow> listNP = g.getNazwaParametrows();
		
		if (listWP.size()==listNP.size()){
			for (int i=0; i<listWP.size(); i++) {
				NazwaParametrow nazwaP = listWP.get(i).getNazwaParametrow();
				
				if(nazwaP.getNazwaParametru().equals(wybranaNP)) {
					WartoscParametrowPK idWPPK = listWP.get(i).getId();
					
					WartoscParametrow wp = wartoscParametrowDAO.find(idWPPK);
					
					if(wartoscP != null) {
						wp.setWartoscParametrow(wartoscP);
						wartoscParametrowDAO.merge(wp);
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, txtMsg.getString("info"), txtMsg.getString("savedSucc")));
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, txtMsg.getString("err"), txtMsg.getString("errOccWhileSav")));
					}
				}
			}
			
		} else {
			WartoscParametrow wp = new WartoscParametrow();
			WartoscParametrowPK wpk = new WartoscParametrowPK();
			
			int NajwId = wartoscParametrowDAO.getMaxWPK();
			
			wpk.setIdNazwaParametrow(nazwaParametrowDAO.getObjectByName(wybranaNP).getIdnazwaParametrow());
			wpk.setIdTowar(towar.getIdtowar());
			wpk.setIdwartoscParametrow(NajwId);
			
			wp.setId(wpk);
			wp.setWartoscParametrow(wartoscP);
			wp.setTowar(towar);
			wp.setNazwaParametrow(nazwaParametrowDAO.getObjectByName(wybranaNP));

			wartoscParametrowDAO.create(wp);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, txtMsg.getString("info"), txtMsg.getString("savedSucc")));
		}
	}
}
