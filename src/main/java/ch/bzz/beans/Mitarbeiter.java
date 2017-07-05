package ch.bzz.beans;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="M_ID", referencedColumnName="BE_ID")
@Table(name = "Mitarbeiter")
public class Mitarbeiter extends Benutzer {

	public Mitarbeiter() {
	}
	
	@Override
	public String toString() {
		return getVorname() + " " + getNachname();
	}

}
