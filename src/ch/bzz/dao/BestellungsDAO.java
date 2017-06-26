package ch.bzz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch.bzz.beans.BestellStatus;
import ch.bzz.beans.Bestellung;
import ch.bzz.beans.Kunde;
import ch.bzz.beans.Mitarbeiter;
import ch.bzz.beans.User;
import ch.bzz.database.HibernateUtil;

public class BestellungsDAO {
	
	public static List<Bestellung> getBestellungenByUser(User user) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
		String hql = "";
		
		if(user instanceof Mitarbeiter) {
			hql = "From Bestellung";
		} else if(user instanceof Kunde) {
			hql = "From Bestellung where kunde.userId=" + user.getUserId();
		}
		
		System.out.println("HQL: " + hql);
		
		Query q = s.createQuery(hql);
		
		List<Bestellung> bestellungen = q.list();
		
		s.close();
		return bestellungen;
	}
	
	public static void save(Bestellung bestellung) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
		s.save(bestellung);
		
		s.close();
	}
	
	public static void setStatus(Bestellung bestellung, String status) {
		BestellStatus bestellStatus = bestellung.getBestellStatus();
		bestellStatus.setStatus(status);
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
		s.update(bestellStatus);
		
		s.close();
	}

}
