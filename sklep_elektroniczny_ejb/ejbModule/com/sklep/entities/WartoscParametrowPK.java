package com.sklep.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the wartosc_parametrow database table.
 * 
 */
@Embeddable
public class WartoscParametrowPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="idwartosc_parametrow")
	private int idwartoscParametrow;

	@Column(name="id_towar", insertable=false, updatable=false)
	private int idTowar;

	@Column(name="id_nazwa_parametrow", insertable=false, updatable=false)
	private int idNazwaParametrow;

	public WartoscParametrowPK() {
	}
	public int getIdwartoscParametrow() {
		return this.idwartoscParametrow;
	}
	public void setIdwartoscParametrow(int idwartoscParametrow) {
		this.idwartoscParametrow = idwartoscParametrow;
	}
	public int getIdTowar() {
		return this.idTowar;
	}
	public void setIdTowar(int idTowar) {
		this.idTowar = idTowar;
	}
	public int getIdNazwaParametrow() {
		return this.idNazwaParametrow;
	}
	public void setIdNazwaParametrow(int idNazwaParametrow) {
		this.idNazwaParametrow = idNazwaParametrow;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WartoscParametrowPK)) {
			return false;
		}
		WartoscParametrowPK castOther = (WartoscParametrowPK)other;
		return 
			(this.idwartoscParametrow == castOther.idwartoscParametrow)
			&& (this.idTowar == castOther.idTowar)
			&& (this.idNazwaParametrow == castOther.idNazwaParametrow);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idwartoscParametrow;
		hash = hash * prime + this.idTowar;
		hash = hash * prime + this.idNazwaParametrow;
		
		return hash;
	}
}