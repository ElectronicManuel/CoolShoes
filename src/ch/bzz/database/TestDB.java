package ch.bzz.database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch.bzz.beans.Bestellung;
import ch.bzz.beans.Kunde;

public class TestDB {

	public static void main(String[] args) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		  
        session.beginTransaction();
 
        
        
        Query q = session.createQuery("From Bestellungen");
        
        List<Bestellung> resultList = q.list();
        
        System.out.println("num of bestellungen:" + resultList.size());
        for (Bestellung next : resultList) {
            System.out.println("next bestellung: " + " " + next.getId() + " " + next.getKunde().getVorname() + " " + next.getBestellStatus().getStatus() + " " + next.getMitarbeiter().getVorname());
        }
	}
	
}
