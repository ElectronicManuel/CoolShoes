package ch.bzz.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import ch.bzz.beans.User;
import ch.bzz.database.DBAction;
import ch.bzz.database.MainDAO;

public class UserDAO {
	
	public static User getUserByLogin(final String username, final String password) throws Exception {
		
		User toReturn = MainDAO.<User>executeAction(new DBAction<User>() {

			@Override
			public User run(Session s) throws Exception {
				@SuppressWarnings("unchecked")
				TypedQuery<User> q = s.createQuery("From User where username = :username AND password = :password");
				q.setParameter("username", username);
				q.setParameter("password", password);
				
				
				User toReturn = null;
				try {
					toReturn = q.getSingleResult();
				} catch(NoResultException ex) {
					
				}
				
				return toReturn;
			}
		});
		
		return toReturn;
	}

}
