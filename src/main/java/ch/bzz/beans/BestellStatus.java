package ch.bzz.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Diese Klasse enthält den Bestellstatus, welcher nur den Status als String und das Bearbeitungsdatum speichert.
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */

@Entity
@Table(name = "BestellStatus")
public class BestellStatus implements Comparable<BestellStatus> {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "BS_ID")
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "BS_B_ID")
	private Bestellung bestellung;
	
	@Column(name = "BS_Status")
	private String status;
	
	@Column(name = "BS_Gesetzt")
	private Date gesetzt;
	
	public BestellStatus() {
	}
	
	public BestellStatus(String status) {
		this.status = status;
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
	 * @return the bestellung
	 */
	public Bestellung getBestellung() {
		return bestellung;
	}

	/**
	 * @param bestellung the bestellung to set
	 */
	public void setBestellung(Bestellung bestellung) {
		this.bestellung = bestellung;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the gesetzt
	 */
	public Date getGesetzt() {
		return gesetzt;
	}

	/**
	 * @param gesetzt the gesetzt to set
	 */
	public void setGesetzt(Date gesetzt) {
		this.gesetzt = gesetzt;
	}

	@Override
	public int compareTo(BestellStatus o) {
		if(getGesetzt() == null || o.getGesetzt() == null) return 0;
		return o.getGesetzt().compareTo(getGesetzt());
	}


}