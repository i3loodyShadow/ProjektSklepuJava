package com.sklep.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rola database table.
 * 
 */
@Entity
@NamedQuery(name="Rola.findAll", query="SELECT r FROM Rola r")
public class Rola implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idrola;

	@Column(name="nazwa_roli")
	private String nazwaRoli;

	//bi-directional many-to-one association to Konto
	@OneToMany(mappedBy="rola")
	private List<Konto> kontos;

	public Rola() {
	}

	public int getIdrola() {
		return this.idrola;
	}

	public void setIdrola(int idrola) {
		this.idrola = idrola;
	}

	public String getNazwaRoli() {
		return this.nazwaRoli;
	}

	public void setNazwaRoli(String nazwaRoli) {
		this.nazwaRoli = nazwaRoli;
	}

	public List<Konto> getKontos() {
		return this.kontos;
	}

	public void setKontos(List<Konto> kontos) {
		this.kontos = kontos;
	}

	public Konto addKonto(Konto konto) {
		getKontos().add(konto);
		konto.setRola(this);

		return konto;
	}

	public Konto removeKonto(Konto konto) {
		getKontos().remove(konto);
		konto.setRola(null);

		return konto;
	}

}