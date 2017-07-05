package ch.bzz.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import ch.bzz.beans.Benutzer;
import ch.bzz.database.DBAction;
import ch.bzz.database.MainDAO;

public class BenutzerDAO {
	
	public static Benutzer getBenutzerByLogin(final String email, final String password) throws Exception {
		
		Benutzer toReturn = MainDAO.<Benutzer>executeAction(new DBAction<Benutzer>() {

			@Override
			public Benutzer run(Session s) throws Exception {
				@SuppressWarnings("unchecked")
				TypedQuery<Benutzer> q = s.createQuery("From Benutzer where email = :email AND passwort = :password");
				q.setParameter("email", email);
				q.setParameter("password", password);
				
				
				Benutzer toReturn = null;
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
