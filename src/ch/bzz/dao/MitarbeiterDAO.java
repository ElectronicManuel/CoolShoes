package ch.bzz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch.bzz.beans.Mitarbeiter;
import ch.bzz.database.HibernateUtil;

public class MitarbeiterDAO {
	
	public static List<Mitarbeiter> getAlleMitarbeiter() {
		List<Mitarbeiter> mitarbeiter;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Query q = session.createQuery("From Mitarbeiter");
        mitarbeiter = q.getResultList();
		
        
        session.close();
		return mitarbeiter;
	}

}
