package ch.bzz.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Mitarbeiter")
public class Mitarbeiter {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "MID")
	private int id;
	
	@Column(name = "MAVorname")
	private String vorname;
	
	@Column(name = "MAName")
	private String nachname;
	
	public Mitarbeiter() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
