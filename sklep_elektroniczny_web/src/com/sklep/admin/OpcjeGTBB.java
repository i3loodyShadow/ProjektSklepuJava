package com.sklep.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.sklep.dao.GrupyTowarowDAO;
import com.sklep.dao.NazwaParametrowDAO;
import com.sklep.entities.GrupyTowarow;
import com.sklep.entities.NazwaParametrow;

@Named
@ViewScoped
public class OpcjeGTBB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final String OPCJE_GT = "/pages/admin/opcjeGrupyTowarow?faces-redirect=true";
	
	private String nazwaGrupy;
	private String nazwaParametruGrupy;
	private String wybranaGrupaTowarow;
	private String wybranaNazwaParemetru;
	private List<String> list = new ArrayList<String>();
	private List<String> NPList = new ArrayList<String>();
	
	@EJB
	GrupyTowarowDAO grupyTowarowDAO;
	
	@EJB
	NazwaParametrowDAO nazwaParametrowDAO;
	
	@Inject
	@ManagedProperty("#{txtMsq}")
	private ResourceBundle txtMsg;
	
	public String getNazwaGrupy() {
		return nazwaGrupy;
	}

	public void setNazwaGrupy(String nazwaGrupy) {
		this.nazwaGrupy = nazwaGrupy;
	}
	
	public List<String> getList() {
		list = grupyTowarowDAO.getNGList();
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
	public String getWybranaGrupaTowarow() {
		return wybranaGrupaTowarow;
	}

	public void setWybranaGrupaTowarow(String wybranaGrupaTowarow) {
		this.wybranaGrupaTowarow = wybranaGrupaTowarow;
	}
	
	public String getNazwaParametruGrupy() {
		return nazwaParametruGrupy;
	}

	public void setNazwaParametruGrupy(String parametrGrupy) {
		this.nazwaParametruGrupy = parametrGrupy;
	}
	
	public List<String> getNPList() {
		NPList = nazwaParametrowDAO.getNPList();
		return NPList;
	}

	public void setNPList(List<String> nPList) {
		NPList = nPList;
	}
	
	public String getWybranaNazwaParemetru() {
		return wybranaNazwaParemetru;
	}

	public void setWybranaNazwaParemetru(String wybranaNazwaParemetru) {
		this.wybranaNazwaParemetru = wybranaNazwaParemetru;
	}
	
	public void dodajGrupe() {
		GrupyTowarow czyPowtorzony = grupyTowarowDAO.getGTByName(nazwaGrupy);
		
		if(czyPowtorzony == null) {
			GrupyTowarow gt = new GrupyTowarow();
			gt.setNazwaGrupy(nazwaGrupy);
			grupyTowarowDAO.create(gt);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, txtMsg.getString("info"), txtMsg.getString("gTSavedSucc")));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, txtMsg.getString("err"), txtMsg.getString("gTExist")));
		}
	}
	
	public void dodajNowaNazweParametrow() {
		NazwaParametrow czyPowtorzony = nazwaParametrowDAO.getObjectByName(nazwaParametruGrupy);
		
		if(czyPowtorzony == null) {
			NazwaParametrow nt = new NazwaParametrow();
			GrupyTowarow gt = grupyTowarowDAO.getGTByName(wybranaGrupaTowarow);
			
			List<GrupyTowarow> list = new ArrayList<GrupyTowarow>();
			list.add(gt);
			
			nt.setNazwaParametru(nazwaParametruGrupy);
			nt.setGrupyTowarows(list);
			
			nazwaParametrowDAO.create(nt);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, txtMsg.getString("info"), txtMsg.getString("nPSavedSucc")));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, txtMsg.getString("err"), txtMsg.getString("nPExist")));
		}
	}
	
	public void dodajNazweParametrow() {
		try {
			NazwaParametrow nt = nazwaParametrowDAO.getObjectByName(wybranaNazwaParemetru);
			GrupyTowarow gt = grupyTowarowDAO.getGTByName(wybranaGrupaTowarow);
			
			List<GrupyTowarow> list = new ArrayList<GrupyTowarow>();
			
			list = nazwaParametrowDAO.pobierzGTDanejNP(wybranaNazwaParemetru);
			list.add(gt);
			
			nt.setGrupyTowarows(list);
			
			nazwaParametrowDAO.merge(nt);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, txtMsg.getString("info"), txtMsg.getString("nPSavedSucc")));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, txtMsg.getString("err"), txtMsg.getString("errOccWhileSav")));
		}
	}
	
	public String doOpcjiGT() {
		return OPCJE_GT;
	}
}
