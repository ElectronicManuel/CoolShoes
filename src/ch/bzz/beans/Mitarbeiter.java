package ch.bzz.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Mitarbeiter")
@PrimaryKeyJoinColumn(name="M_U_ID", referencedColumnName="U_ID")
public class Mitarbeiter extends User {

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
	
	@Override
	public String toString() {
		return getVorname() + " " + getNachname();
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
