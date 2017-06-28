package ch.bzz.database;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainDAO {
	
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
