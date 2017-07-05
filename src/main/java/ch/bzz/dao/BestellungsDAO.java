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

public class BestellungsDAO {
	
	public static List<Bestellung> getBestellungenByUser(final Benutzer user) throws Exception {
		List<Bestellung> bestellungen = MainDAO.<List<Bestellung>>executeAction(new DBAction<List<Bestellung>>() {
			
			@Override
			public List<Bestellung> run(Session s) {
				String hql = "";
				
				if(user instanceof Mitarbeiter) {
					hql = "From Bestellung";
				} else if(user instanceof Kunde) {
					hql = "From Bestellung where kunde.benuzerId=" + user.getBenutzerId();
				}
				
				@SuppressWarnings("unchecked")
				TypedQuery<Bestellung> q = s.createQuery(hql);
				
				List<Bestellung> bestellungen = q.getResultList();
				
				return bestellungen;
			}
			
		});
		
		return bestellungen;
	}
	
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
				
				s.saveOrUpdate(status);
				
				return null;
			}
			
		});
	}
	
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
