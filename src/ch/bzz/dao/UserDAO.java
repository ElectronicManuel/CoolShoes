package ch.bzz.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch.bzz.beans.User;
import ch.bzz.database.HibernateUtil;

public class UserDAO {
	
	public static User getUserByLogin(String username, String password) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
		Query q = s.createQuery("From User where username = :username AND password = :password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		
		List<User> users = q.list();
		if(users.size() > 0) {
			return users.get(0);
		}
		
		s.close();
		
		return null;
	}

}
