package jsf.sklep.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grupy_towarow database table.
 * 
 */
@Entity
@Table(name="grupy_towarow")
@NamedQuery(name="GrupyTowarow.findAll", query="SELECT g FROM GrupyTowarow g")
public class GrupyTowarow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idgrupy_towarow")
	private int idgrupyTowarow;

	@Column(name="nazwa_grupy")
	private String nazwaGrupy;

	//bi-directional many-to-many association to NazwaParametrow
	@ManyToMany(mappedBy="grupyTowarows")
	private List<NazwaParametrow> nazwaParametrows;

	//bi-directional many-to-one association to Towar
	@OneToMany(mappedBy="grupyTowarow")
	private List<Towar> towars;

	public GrupyTowarow() {
	}

	public int getIdgrupyTowarow() {
		return this.idgrupyTowarow;
	}

	public void setIdgrupyTowarow(int idgrupyTowarow) {
		this.idgrupyTowarow = idgrupyTowarow;
	}

	public String getNazwaGrupy() {
		return this.nazwaGrupy;
	}

	public void setNazwaGrupy(String nazwaGrupy) {
		this.nazwaGrupy = nazwaGrupy;
	}

	public List<NazwaParametrow> getNazwaParametrows() {
		return this.nazwaParametrows;
	}

	public void setNazwaParametrows(List<NazwaParametrow> nazwaParametrows) {
		this.nazwaParametrows = nazwaParametrows;
	}

	public List<Towar> getTowars() {
		return this.towars;
	}

	public void setTowars(List<Towar> towars) {
		this.towars = towars;
	}

	public Towar addTowar(Towar towar) {
		getTowars().add(towar);
		towar.setGrupyTowarow(this);

		return towar;
	}

	public Towar removeTowar(Towar towar) {
		getTowars().remove(towar);
		towar.setGrupyTowarow(null);

		return towar;
	}

}