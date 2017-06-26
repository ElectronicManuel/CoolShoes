package ch.bzz.database;

import org.hibernate.Query;
import org.hibernate.Session;

import ch.bzz.beans.Kunde;
import ch.bzz.beans.Mitarbeiter;

public class TestDB {

	public static void main(String[] args) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		  
        session.beginTransaction();
 
        Query q = session.createQuery("From Bestellung");
        
        System.out.println(q.list().size());
        
        
        session.close();
        System.exit(0);
	}
	
}
