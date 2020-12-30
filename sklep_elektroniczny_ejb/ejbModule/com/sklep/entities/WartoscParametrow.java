package com.sklep.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wartosc_parametrow database table.
 * 
 */
@Entity
@Table(name="wartosc_parametrow")
@NamedQuery(name="WartoscParametrow.findAll", query="SELECT w FROM WartoscParametrow w")
public class WartoscParametrow implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WartoscParametrowPK id;

	@Column(name="wartosc_parametrow")
	private String wartoscParametrow;

	//bi-directional many-to-one association to NazwaParametrow
	@ManyToOne
	@JoinColumn(name="id_nazwa_parametrow")
	private NazwaParametrow nazwaParametrow;

	//bi-directional many-to-one association to Towar
	@ManyToOne
	@JoinColumn(name="id_towar")
	private Towar towar;

	public WartoscParametrow() {
	}

	public WartoscParametrowPK getId() {
		return this.id;
	}

	public void setId(WartoscParametrowPK id) {
		this.id = id;
	}

	public String getWartoscParametrow() {
		return this.wartoscParametrow;
	}

	public void setWartoscParametrow(String wartoscParametrow) {
		this.wartoscParametrow = wartoscParametrow;
	}

	public NazwaParametrow getNazwaParametrow() {
		return this.nazwaParametrow;
	}

	public void setNazwaParametrow(NazwaParametrow nazwaParametrow) {
		this.nazwaParametrow = nazwaParametrow;
	}

	public Towar getTowar() {
		return this.towar;
	}

	public void setTowar(Towar towar) {
		this.towar = towar;
	}

}