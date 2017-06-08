package ch.bzz.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "BestellStatus")
public class BestellStatus {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "BSID")
	private int id;
	
	@Column(name = "Bestellnummer")
	private String bestellNummer;
	
	@Column(name = "Status")
	private String status;
	
	@Column(name = "Bearbeitung")
	private Date bearbeitungsDatum;
	
	@Column(name = "Lieferunggeplant")
	private Date lieferungGeplant;
	
	public BestellStatus() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBestellNummer() {
		return bestellNummer;
	}

	public void setBestellNummer(String bestellNummer) {
		this.bestellNummer = bestellNummer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBearbeitungsDatum() {
		return bearbeitungsDatum;
	}

	public void setBearbeitungsDatum(Date bearbeitungsDatum) {
		this.bearbeitungsDatum = bearbeitungsDatum;
	}

	public Date getLieferungGeplant() {
		return lieferungGeplant;
	}

	public void setLieferungGeplant(Date lieferungGeplant) {
		this.lieferungGeplant = lieferungGeplant;
	}
	
}
