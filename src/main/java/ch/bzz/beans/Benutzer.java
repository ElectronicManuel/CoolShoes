package ch.bzz.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Diese Klasse enthält Daten zum Benutzer, welcher entweder Kunde oder Mitarbeiter ist
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */

@Entity
@Table(name="Benutzer")
@Inheritance(strategy=InheritanceType.JOINED)
public class Benutzer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "BE_ID")
	private int benutzerId;
	
	@Column(name = "BE_Vorname")
	private String vorname;
	
	@Column(name = "BE_Nachname")
	private String nachname;
	
	@Column(name = "BE_Email")
	private String email;
	
	@Column(name = "BE_Passwort")
	private String passwort;

	public Benutzer() {
	}

	public int getBenutzerId() {
		return benutzerId;
	}

	/**
	 * @return the vorname
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * @param vorname the vorname to set
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * @return the nachname
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * @param nachname the nachname to set
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the passwort
	 */
	public String getPasswort() {
		return passwort;
	}

	/**
	 * @param passwort the passwort to set
	 */
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	/**
	 * @param benutzerId the benutzerId to set
	 */
	public void setBenutzerId(int benutzerId) {
		this.benutzerId = benutzerId;
	}

	
}
