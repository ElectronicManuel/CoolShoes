package ch.bzz.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import ch.bzz.beans.Benutzer;
import ch.bzz.database.DBAction;
import ch.bzz.database.MainDAO;

/**
 * Diese Klasse enthält Methoden Benutzer aus der Datenbank auszulesen / zu schreiben
 * Alle Methoden in dieser Klasse sind statisch da diese keine Instanz benötigen 
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */
public class BenutzerDAO {
	
	/**
	 * Sucht in der DB nach einem Benutzer mit der email und dem Passwort und gibt diesen (oder null) zurück
	 * @param email Email des Benutzers
	 * @param password Passwort des Benutzers
	 * @return Benutzer mit den Daten oder Null falls keiner vorhanden
	 * @throws Exception - Falls ein Problem mit der Datenbankverbindung aufkam
	 */
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
