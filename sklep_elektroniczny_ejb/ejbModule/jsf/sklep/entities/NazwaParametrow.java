package jsf.sklep.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nazwa_parametrow database table.
 * 
 */
@Entity
@Table(name="nazwa_parametrow")
@NamedQuery(name="NazwaParametrow.findAll", query="SELECT n FROM NazwaParametrow n")
public class NazwaParametrow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idnazwa_parametrow")
	private int idnazwaParametrow;

	@Column(name="nazwa_parametru")
	private String nazwaParametru;

	//bi-directional many-to-many association to GrupyTowarow
	@ManyToMany
	@JoinTable(
		name="grupy_towarow_has_nazwa_parametrow"
		, joinColumns={
			@JoinColumn(name="nazwa_parametrow_idnazwa_parametrow")
			}
		, inverseJoinColumns={
			@JoinColumn(name="grupy_towarow_idgrupy_towarow")
			}
		)
	private List<GrupyTowarow> grupyTowarows;

	//bi-directional many-to-one association to WartoscParametrow
	@OneToMany(mappedBy="nazwaParametrow")
	private List<WartoscParametrow> wartoscParametrows;

	public NazwaParametrow() {
	}

	public int getIdnazwaParametrow() {
		return this.idnazwaParametrow;
	}

	public void setIdnazwaParametrow(int idnazwaParametrow) {
		this.idnazwaParametrow = idnazwaParametrow;
	}

	public String getNazwaParametru() {
		return this.nazwaParametru;
	}

	public void setNazwaParametru(String nazwaParametru) {
		this.nazwaParametru = nazwaParametru;
	}

	public List<GrupyTowarow> getGrupyTowarows() {
		return this.grupyTowarows;
	}

	public void setGrupyTowarows(List<GrupyTowarow> grupyTowarows) {
		this.grupyTowarows = grupyTowarows;
	}

	public List<WartoscParametrow> getWartoscParametrows() {
		return this.wartoscParametrows;
	}

	public void setWartoscParametrows(List<WartoscParametrow> wartoscParametrows) {
		this.wartoscParametrows = wartoscParametrows;
	}

	public WartoscParametrow addWartoscParametrow(WartoscParametrow wartoscParametrow) {
		getWartoscParametrows().add(wartoscParametrow);
		wartoscParametrow.setNazwaParametrow(this);

		return wartoscParametrow;
	}

	public WartoscParametrow removeWartoscParametrow(WartoscParametrow wartoscParametrow) {
		getWartoscParametrows().remove(wartoscParametrow);
		wartoscParametrow.setNazwaParametrow(null);

		return wartoscParametrow;
	}

}