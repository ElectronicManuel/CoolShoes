package ch.bzz.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Bestellungen")
public class Bestellung {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "BID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="FKKunde")
	private Kunde kunde;
	
	@ManyToOne
	@JoinColumn(name="FKStatus")
	private BestellStatus bestellStatus;
	
	@ManyToOne
	@JoinColumn(name="FKMitarbeiter")
	private Mitarbeiter mitarbeiter;
	
	public Bestellung() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return getId() + "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public BestellStatus getBestellStatus() {
		return bestellStatus;
	}

	public void setBestellStatus(BestellStatus bestellStatus) {
		this.bestellStatus = bestellStatus;
	}

	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}

	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}
	
	
}
