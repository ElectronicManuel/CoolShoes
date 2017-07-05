package ch.bzz.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

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

	public void setBenutzerId(int benutzerId) {
		this.benutzerId = benutzerId;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
}
