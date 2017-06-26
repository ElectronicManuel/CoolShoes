package ch.bzz.controller;

import ch.bzz.beans.Kunde;
import ch.bzz.beans.Mitarbeiter;
import ch.bzz.beans.User;
import ch.bzz.dao.UserDAO;

public class LoginController {
	
	private User user;
	
	public LoginController() {
		
	}

	public void login(String username, String password) {
		User loggedIn = UserDAO.getUserByLogin(username, password);
		if(loggedIn != null) {
			this.user = loggedIn;
			if(loggedIn instanceof Kunde) {
				// Der Benutzer ist Kunde
				MainController.getInstance().getMainGui().getLoginTab().setMessage("Kunde - " + ((Kunde)loggedIn).getEmail());
			} else if(loggedIn instanceof Mitarbeiter) {
				// Der Benutzer ist Mitarbeiter
				MainController.getInstance().getMainGui().getLoginTab().setMessage("Mitarbeiter - " + ((Mitarbeiter)loggedIn).getVorname());
			}
			MainController.getInstance().getMainGui().getLoginTab().getLoginButton().setEnabled(true);
			MainController.getInstance().setLoggedIn(true);
		} else {
			// Kein Benutzer mit diesem Username + Passwort gefunden
			MainController.getInstance().getMainGui().getLoginTab().setMessage("<html><span style=\"color:red\">Ungültige Daten</span>");
			MainController.getInstance().getMainGui().getLoginTab().getLoginButton().setEnabled(true);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
