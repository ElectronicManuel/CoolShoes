package ch.bzz.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Diese Klasse initialisiert Hibernate und konfiguriert diese der Konfiguration entsprechend
 * Diese Klasse wurde übernommen von: https://stackoverflow.com/questions/32594554/hibernate-and-mysql-configuration
 * @author G.Spansky
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */
public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	  
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
  
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
  
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
    
}
