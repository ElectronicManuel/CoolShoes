package ch.bzz.controller;

import javax.swing.JOptionPane;

import ch.bzz.beans.Benutzer;
import ch.bzz.beans.Kunde;
import ch.bzz.beans.Mitarbeiter;
import ch.bzz.dao.BenutzerDAO;

/**
 * Diese Klasse ist für alles zuständig was mit dem Anmelden zu tun hat
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */
public class LoginController {
	
	private Benutzer user;
	
	public LoginController() {
		
	}

	/**
	 * Sucht einen Benutzer mit passender email und passwort, falls vorhanden wird diese in dem Attribut 'user' gespeichert und im gui auf den bestellungstab gewechselt
	 * 
	 * @param email
	 * @param password
	 * @return true wenn benutzer gefunden, false wenn nicht
	 */
	public boolean login(String email, String password) {
		try {
			Benutzer loggedIn = BenutzerDAO.getBenutzerByLogin(email, password);
			if(loggedIn != null) {
				this.user = loggedIn;
				if(loggedIn instanceof Kunde) {
					// Der Benutzer ist Kunde
				} else if(loggedIn instanceof Mitarbeiter) {
					// Der Benutzer ist Mitarbeiter
				}
				if(MainController.isGuiEnabled()) {
					MainController.getInstance().setLoggedIn(true);
				}
				return true;
			} else {
				// Kein Benutzer mit diesem Username + Passwort gefunden
				MainController.getInstance().popup("Login fehlgeschlagen", "Ihr Benutzername oder Passwort ist inkorrekt", JOptionPane.ERROR_MESSAGE);
			}
		} catch(Exception ex) {
			System.out.println("Error caught");
			ex.printStackTrace();
			MainController.getInstance().popup("Login fehlgeschlagen", "Ein Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
			
		}
		return false;
	}

	/**
	 * @return the user
	 */
	public Benutzer getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Benutzer user) {
		this.user = user;
	}

	
}
