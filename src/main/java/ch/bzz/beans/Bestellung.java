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

	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}

	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

	public List<BestellStatus> getBestellStati() {
		Collections.sort(bestellStati);
		return bestellStati;
	}

	public void setBestellStati(List<BestellStatus> bestellStati) {
		this.bestellStati = bestellStati;
	}

	public String getVermerk() {
		return vermerk;
	}

	public void setVermerk(String vermerk) {
		this.vermerk = vermerk;
	}

	public Date getGeplanteLieferung() {
		return geplanteLieferung;
	}

	public void setGeplanteLieferung(Date geplanteLieferung) {
		this.geplanteLieferung = geplanteLieferung;
	}

}
