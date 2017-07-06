package ch.bzz.beans;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Diese Klasse enthält Daten für Mitarbeiter, allerdings gibt es keine, daher dient diese Klasse lediglich dazu Kunden von Mitarbeitern zu unterscheiden
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */

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
