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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bestellung getBestellung() {
		return bestellung;
	}

	public void setBestellung(Bestellung bestellung) {
		this.bestellung = bestellung;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getGesetzt() {
		return gesetzt;
	}

	public void setGesetzt(Date gesetzt) {
		this.gesetzt = gesetzt;
	}

	@Override
	public int compareTo(BestellStatus o) {
		if(getGesetzt() == null || o.getGesetzt() == null) return 0;
		return getGesetzt().compareTo(o.getGesetzt());
	}


}