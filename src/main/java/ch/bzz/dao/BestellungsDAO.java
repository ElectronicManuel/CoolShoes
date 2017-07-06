package ch.bzz.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import ch.bzz.beans.Benutzer;
import ch.bzz.beans.BestellStatus;
import ch.bzz.beans.Bestellung;
import ch.bzz.beans.Kunde;
import ch.bzz.beans.Mitarbeiter;
import ch.bzz.database.DBAction;
import ch.bzz.database.MainDAO;

/**
 * Diese Klasse enthält Methoden um Bestellungen und Bestellstati in die Datenbank zu schreiben / auszulesen
 * Alle Methoden in dieser Klasse sind statisch da keine Instanz benötigt wird
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */
public class BestellungsDAO {
	
	/**
	 * Überprüft ob der Benutzer Mitarbeiter ist und gibt alle Bestellungen, oder nur die des Kunden falls der Benutzer ein Kunde ist, zurück
	 * 
	 * @param user Der zu prüfende Benutzer
	 * @return Die Liste aller Bestellungen, falls keine Bestellungen vorhanden ist dies eine leere Liste
	 * @throws Exception - Falls ein Datenbankfehler auftrat
	 */
	public static List<Bestellung> getBestellungenByUser(final Benutzer user) throws Exception {
		List<Bestellung> bestellungen = MainDAO.<List<Bestellung>>executeAction(new DBAction<List<Bestellung>>() {
			
			@Override
			public List<Bestellung> run(Session s) {
				String hql = "";
				
				if(user instanceof Mitarbeiter) {
					hql = "From Bestellung";
				} else if(user instanceof Kunde) {
					hql = "From Bestellung where kunde.benutzerId=" + user.getBenutzerId();
				}
				
				@SuppressWarnings("unchecked")
				TypedQuery<Bestellung> q = s.createQuery(hql);
				
				List<Bestellung> bestellungen = q.getResultList();
				
				return bestellungen;
			}
			
		});
		
		return bestellungen;
	}
	
	/**
	 * Setzt den Bestellstatus einer Bestellung, falls dieser schon einmal gesetzt wurde wird bei dem bereits existierenden Status das Bearbeitungsdatum auf now geändert, ansonsten wird ein neuer erstellt
	 * 
	 * @param bestellung
	 * @param bestellStatus
	 * @throws Exception - Falls ein Datenbankfehler auftrat
	 */
	public static void setBestellStatus(final Bestellung bestellung, final String bestellStatus) throws Exception {
		MainDAO.executeAction(new DBAction<Object>() {

			@Override
			public Object run(Session s) throws Exception {
				BestellStatus status = new BestellStatus();
				for(BestellStatus statusIter : bestellung.getBestellStati()) {
					if(statusIter.getStatus().equals(bestellStatus)) {
						status = statusIter;
						break;
					}
				}
				
				status.setStatus(bestellStatus);
				status.setGesetzt(new Date());
				status.setBestellung(bestellung);
				
				s.saveOrUpdate(status);
				
				return null;
			}
			
		});
	}
	
	/**
	 * Speichert eine Bestellung in der Datenbank
	 * 
	 * @param bestellung
	 * @throws Exception - Falls ein Datenbankfehler auftrat
	 */
	public static void save(final Bestellung bestellung) throws Exception {
		MainDAO.executeAction(new DBAction<Object>() {
			
			@Override
			public Object run(Session s) {
				s.saveOrUpdate(bestellung);
				
				return null;
			}
			
		});
	}
	
}
