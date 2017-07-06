package ch.bzz.database;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Diese Klasse ermöglicht die einheitliche Initialisierung und Beendung einer Hibernate Transaktion mithilfe des Callback Objektes DBAction
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */
public class MainDAO {
	
	/**
	 * Führt die run Methode des DBAction Callback Objektes aus nachdem es die Transaktion initialisiert hat und gibt dann ein Objekt mit dem entsprechenden Typen zurück nachdem die Transaktion beendet wurde
	 * 
	 * @param action - Callback Object mit auszuführender Implementation
	 * @return Objekt mit dem der erhaltenen DBAction entsprechenden Typen
	 * @throws Exception
	 */
	public static <T> T executeAction(DBAction<T> action) throws Exception {
		T toReturn = null;
		
		Exception toThrow = null;
		
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			
			toReturn = action.run(session);
			
			trns.commit();
		}
		catch(Exception ex) {
			if(trns != null) {
				trns.rollback();
			}
			ex.printStackTrace();
			toThrow = ex;
		}
		finally {
			session.close();
		}
		
		if(toThrow != null) throw toThrow;
		
		return toReturn;
	}

}
