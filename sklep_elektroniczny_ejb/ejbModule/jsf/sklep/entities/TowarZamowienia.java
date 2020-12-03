package jsf.sklep.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the towar_zamowienia database table.
 * 
 */
@Entity
@Table(name="towar_zamowienia")
@NamedQuery(name="TowarZamowienia.findAll", query="SELECT t FROM TowarZamowienia t")
public class TowarZamowienia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idtowar_zamowienia")
	private int idtowarZamowienia;

	private String cena;

	private String model;

	private String producent;

	//bi-directional many-to-one association to Zamowienie
	@ManyToOne
	private Zamowienie zamowienie;

	public TowarZamowienia() {
	}

	public int getIdtowarZamowienia() {
		return this.idtowarZamowienia;
	}

	public void setIdtowarZamowienia(int idtowarZamowienia) {
		this.idtowarZamowienia = idtowarZamowienia;
	}

	public String getCena() {
		return this.cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
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

	public Zamowienie getZamowienie() {
		return this.zamowienie;
	}

	public void setZamowienie(Zamowienie zamowienie) {
		this.zamowienie = zamowienie;
	}

}