package jsf.sklep.entities;

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

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idwartosc_parametrow")
	private int idwartoscParametrow;

	@Column(name="wartosc_parametrow")
	private String wartoscParametrow;

	//bi-directional many-to-one association to NazwaParametrow
	@ManyToOne
	@JoinColumn(name="nazwa_parametrow_idnazwa_parametrow")
	private NazwaParametrow nazwaParametrow;

	//bi-directional many-to-one association to Towar
	@ManyToOne
	private Towar towar;

	public WartoscParametrow() {
	}

	public int getIdwartoscParametrow() {
		return this.idwartoscParametrow;
	}

	public void setIdwartoscParametrow(int idwartoscParametrow) {
		this.idwartoscParametrow = idwartoscParametrow;
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