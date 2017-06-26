package ch.bzz.dao;

import java.util.logging.Logger;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import ch.bzz.beans.User;
import ch.bzz.database.HibernateUtil;

public class UserDAO {
	
	public static User getUserByLogin(String username, String password) throws Exception {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		
		TypedQuery<User> q = s.createQuery("From User where username = :username AND password = :password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		
		
		User toReturn = q.getSingleResult();
		
		s.close();
		
		return toReturn;
	}

}
