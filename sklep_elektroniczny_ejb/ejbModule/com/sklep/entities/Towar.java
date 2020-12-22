package com.sklep.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the towar database table.
 * 
 */
@Entity
@NamedQuery(name="Towar.findAll", query="SELECT t FROM Towar t")
public class Towar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtowar;

	private String model;

	private String producent;

	//bi-directional many-to-one association to GrupyTowarow
	@ManyToOne
	@JoinColumn(name="id_grupy_towarow")
	private GrupyTowarow grupyTowarow;

	//bi-directional many-to-one association to WartoscParametrow
	@OneToMany(mappedBy="towar")
	private List<WartoscParametrow> wartoscParametrows;

	public Towar() {
	}

	public int getIdtowar() {
		return this.idtowar;
	}

	public void setIdtowar(int idtowar) {
		this.idtowar = idtowar;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProducent() {
		return this.producent;
	}

	public void setProducent(String producent) {
		this.producent = producent;
	}

	public GrupyTowarow getGrupyTowarow() {
		return this.grupyTowarow;
	}

	public void setGrupyTowarow(GrupyTowarow grupyTowarow) {
		this.grupyTowarow = grupyTowarow;
	}

	public List<WartoscParametrow> getWartoscParametrows() {
		return this.wartoscParametrows;
	}

	public void setWartoscParametrows(List<WartoscParametrow> wartoscParametrows) {
		this.wartoscParametrows = wartoscParametrows;
	}

	public WartoscParametrow addWartoscParametrow(WartoscParametrow wartoscParametrow) {
		getWartoscParametrows().add(wartoscParametrow);
		wartoscParametrow.setTowar(this);

		return wartoscParametrow;
	}

	public WartoscParametrow removeWartoscParametrow(WartoscParametrow wartoscParametrow) {
		getWartoscParametrows().remove(wartoscParametrow);
		wartoscParametrow.setTowar(null);

		return wartoscParametrow;
	}

}