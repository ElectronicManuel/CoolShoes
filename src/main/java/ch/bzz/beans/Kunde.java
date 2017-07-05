package ch.bzz.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="K_ID", referencedColumnName="BE_ID")
@Table(name="Kunde")
public class Kunde extends Benutzer {

	@Column(name = "K_Adresse")
	private String adresse;
	
	@Column(name = "K_PLZ")
	private int plz;
	
	@Column(name = "K_Ort")
	private String ort;
	
	public Kunde() {
	}

	@Override
	public String toString() {
		return getVorname() + " " + getNachname() + " " + getAdresse();
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

}
