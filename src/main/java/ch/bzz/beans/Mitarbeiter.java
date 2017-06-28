package ch.bzz.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="MID", referencedColumnName="U_ID")
@Table(name = "Mitarbeiter")
public class Mitarbeiter extends User {

	@Column(name = "MAVorname")
	private String vorname;
	
	@Column(name = "MAName")
	private String nachname;
	
	public Mitarbeiter() {
	}
	
	@Override
	public String toString() {
		return getVorname() + " " + getNachname();
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
	
}
