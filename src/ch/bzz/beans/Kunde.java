package ch.bzz.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="KID", referencedColumnName="U_ID")
@Table(name="Kunde")
public class Kunde extends User {

	@Column(name = "KVorname")
	private String vorname;
	
	@Column(name = "KName")
	private String nachname;
	
	@Column(name = "KAdresse")
	private String adresse;
	
	@Column(name = "KPLZ")
	private int plz;
	
	@Column(name = "KOrt")
	private String ort;
	
	@Column(name = "KEmailAdresse")
	private String email;
	
	@Column(name = "KPWD")
	private String pwd;
	
	public Kunde() {
	}

	@Override
	public String toString() {
		return getVorname() + " " + getNachname() + " " + getAdresse();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
