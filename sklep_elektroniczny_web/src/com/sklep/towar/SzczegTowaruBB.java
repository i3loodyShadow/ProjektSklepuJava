package com.sklep.towar;

import java.io.IOException;
import java.io.Serializable;
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
import com.sklep.dao.TowarDAO;
import com.sklep.entities.Towar;
import com.sklep.entities.WartoscParametrow;

@Named
@ViewScoped
public class SzczegTowaruBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Towar towar = new Towar();
	private Towar loaded = new Towar();
	private String prod;
	private String mod;
	private List<WartoscParametrow> list;

	@EJB
	TowarDAO TowarDAO;

	@Inject
	@ManagedProperty("#{txtMsq}")
	private ResourceBundle txtMsg;

	@Inject
	Flash flash;

	public Towar getTowar() {
		return towar;
	}
	
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

	public List<WartoscParametrow> getList() {
		return list;
	}

	public void setList(List<WartoscParametrow> list) {
		this.list = list;
	}

	public void onLoad() throws IOException {

		loaded = (Towar) flash.get("towar");

		if (loaded != null) {
			towar = loaded;

			int idTowaru = towar.getIdtowar();
			
			setProd(towar.getProducent());
			
			setMod(towar.getModel());
			
			list = TowarDAO.getTowarDetails(idTowaru).getWartoscParametrows();

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, txtMsg.getString("err"), txtMsg.getString("wrongSysUsage")));
		}
	}

}
