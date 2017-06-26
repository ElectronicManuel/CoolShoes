package ch.bzz.database;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainDAO {
	
	@SuppressWarnings("unchecked")
	public static <T> T executeAction(DBAction action, Class<T> type) throws Exception {
		T toReturn = null;
		
		Exception toThrow = null;
		
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			
			if(type != null) {
				toReturn = (T) action.run(session);
			} else {
				action.run(session);
			}
			
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
