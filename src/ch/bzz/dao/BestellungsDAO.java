package ch.bzz.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import ch.bzz.beans.Bestellung;
import ch.bzz.beans.Kunde;
import ch.bzz.beans.Mitarbeiter;
import ch.bzz.beans.User;
import ch.bzz.database.DBAction;
import ch.bzz.database.MainDAO;

public class BestellungsDAO {
	
	public static List<Bestellung> getBestellungenByUser(final User user) throws Exception {
		@SuppressWarnings("unchecked")
		List<Bestellung> bestellungen = (List<Bestellung>) MainDAO.executeAction(new DBAction() {
			
			@Override
			public Object run(Session s) {
				String hql = "";
				
				if(user instanceof Mitarbeiter) {
					hql = "From Bestellung";
				} else if(user instanceof Kunde) {
					hql = "From Bestellung where kunde.userId=" + user.getUserId();
				}
				
				TypedQuery<Bestellung> q = s.createQuery(hql);
				
				List<Bestellung> bestellungen = q.getResultList();
				
				return bestellungen;
			}
			
		}, List.class);
		
		return bestellungen;
	}
	
	public static void save(final Bestellung bestellung) throws Exception {
		MainDAO.executeAction(new DBAction() {
			
			@Override
			public Object run(Session s) {
				s.saveOrUpdate(bestellung.getBestellStatus());
				s.saveOrUpdate(bestellung.getMitarbeiter());
				s.saveOrUpdate(bestellung);
				
				return null;
			}
			
		}, null);
	}
	
}
