package ch.bzz.database;

import org.hibernate.Session;

import ch.bzz.beans.Mitarbeiter;
import ch.bzz.beans.User;

public class TestDB {

	public static void main(String[] args) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		  
        session.beginTransaction();
 
        User u = session.get(User.class, 1);
        
        System.out.println(u.getUserId() + " " + u.getUsername() + " " + (u instanceof Mitarbeiter));
        
        session.close();
	}
	
}
