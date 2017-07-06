package ch.bzz.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Diese Klasse enthält Kundenspezifische Daten
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */

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
		return getVorname() + " " + getNachname();
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the plz
	 */
	public int getPlz() {
		return plz;
	}

	/**
	 * @param plz the plz to set
	 */
	public void setPlz(int plz) {
		this.plz = plz;
	}

	/**
	 * @return the ort
	 */
	public String getOrt() {
		return ort;
	}

	/**
	 * @param ort the ort to set
	 */
	public void setOrt(String ort) {
		this.ort = ort;
	}


}
