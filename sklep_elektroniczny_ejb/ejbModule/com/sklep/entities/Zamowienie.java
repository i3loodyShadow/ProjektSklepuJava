package com.sklep.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the zamowienie database table.
 * 
 */
@Entity
@NamedQuery(name="Zamowienie.findAll", query="SELECT z FROM Zamowienie z")
public class Zamowienie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idzamowienie;

	private String koszt;

	private int status;

	//bi-directional many-to-one association to TowarZamowienia
	@OneToMany(mappedBy="zamowienie")
	private List<TowarZamowienia> towarZamowienias;

	//bi-directional many-to-one association to Konto
	@ManyToOne
	private Konto konto;

	public Zamowienie() {
	}

	public int getIdzamowienie() {
		return this.idzamowienie;
	}

	public void setIdzamowienie(int idzamowienie) {
		this.idzamowienie = idzamowienie;
	}

	public String getKoszt() {
		return this.koszt;
	}

	public void setKoszt(String koszt) {
		this.koszt = koszt;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<TowarZamowienia> getTowarZamowienias() {
		return this.towarZamowienias;
	}

	public void setTowarZamowienias(List<TowarZamowienia> towarZamowienias) {
		this.towarZamowienias = towarZamowienias;
	}

	public TowarZamowienia addTowarZamowienia(TowarZamowienia towarZamowienia) {
		getTowarZamowienias().add(towarZamowienia);
		towarZamowienia.setZamowienie(this);

		return towarZamowienia;
	}

	public TowarZamowienia removeTowarZamowienia(TowarZamowienia towarZamowienia) {
		getTowarZamowienias().remove(towarZamowienia);
		towarZamowienia.setZamowienie(null);

		return towarZamowienia;
	}

	public Konto getKonto() {
		return this.konto;
	}

	public void setKonto(Konto konto) {
		this.konto = konto;
	}

}