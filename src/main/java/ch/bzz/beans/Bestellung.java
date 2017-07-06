package ch.bzz.beans;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Diese Klasse enthält Bestellungen welche einem Kunden und einem Mitarbeiter zugewiesen sind.
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */

@Entity
@Table(name = "Bestellung")
public class Bestellung {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "B_ID")
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="B_K_ID")
	private Kunde kunde;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="B_M_ID")
	private Mitarbeiter mitarbeiter;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bestellung", fetch = FetchType.EAGER)
	private List<BestellStatus> bestellStati;
	
	@Column(name = "B_Vermerk")
	private String vermerk;
	
	@Column(name = "B_GeplLieferung")
	private Date geplanteLieferung;
	
	public Bestellung() {
	}
	
	@Override
	public String toString() {
		return getId() + "";
	}
	
	public BestellStatus getCurrentBestellStatus() {
		if(bestellStati.size() > 0) {
			return getBestellStati().get(0);
		}
		
		return null;
	}

	/**
	 * 
	 * @return Bestellstati sortiert nach Bearbeitungsdatum
	 */
	public List<BestellStatus> getBestellStati() {
		Collections.sort(bestellStati);
		return bestellStati;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the kunde
	 */
	public Kunde getKunde() {
		return kunde;
	}

	/**
	 * @param kunde the kunde to set
	 */
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	/**
	 * @return the mitarbeiter
	 */
	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}

	/**
	 * @param mitarbeiter the mitarbeiter to set
	 */
	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

	/**
	 * @return the vermerk
	 */
	public String getVermerk() {
		return vermerk;
	}

	/**
	 * @param vermerk the vermerk to set
	 */
	public void setVermerk(String vermerk) {
		this.vermerk = vermerk;
	}

	/**
	 * @return the geplanteLieferung
	 */
	public Date getGeplanteLieferung() {
		return geplanteLieferung;
	}

	/**
	 * @param bestellStati the bestellStati to set
	 */
	public void setBestellStati(List<BestellStatus> bestellStati) {
		this.bestellStati = bestellStati;
	}

	public void setGeplanteLieferung(Date geplanteLieferung) {
		this.geplanteLieferung = geplanteLieferung;
	}

}
