package ch.bzz.controller;

import javax.swing.JOptionPane;

import ch.bzz.gui.MainGui;

/**
 * Dies ist die Kernklasse der Implementation, diese Klasse verwaltet alle anderen Controller und verwaltet genau die GUI Klassen.
 * Des weiteren enthält diese Klasse die main methode
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */
public class MainController {
	
	public static void main(String[] args) {
		new MainController();
		MainController.getInstance().initGui();
	}
	
	// Singleton
	private static MainController instance;
	public static MainController getInstance() {
		return instance;
	}
	
	// Controller Klassen
	private LoginController loginCtrl;
	
	// Gui Klassen
	private MainGui mainGui;
	
	public MainController() {
		instance = this;
		initControllers();
	}
	
	/**
	 * Initialisiert das GUI, wenn diese Methode nicht aufgerufen wird, kann das Programm ohne GUI benutzt werden (Junit)
	 */
	public void initGui() {
		mainGui = new MainGui();
	}
	
	/**
	 * Diese Method initialisiert alle Controller und wird immer im Konstruktor ausgeführt
	 */
	private void initControllers() {
		loginCtrl = new LoginController();
	}
	
	/**
	 * 
	 * @return true wenn das GUI initialisiert ist, false wenn nicht
	 */
	public static boolean isGuiEnabled() {
		return getInstance().mainGui != null;
	}
	
	/**
	 * Zeigt eine Meldung auf dem Bildschirm an, falls das GUI nicht aktiv ist, wird die Nachricht in der Konsole ausgegeben
	 * 
	 * @param title Titel der Nachricht
	 * @param msg Die Nachricht
	 * @param messageType Die Art der Nachricht
	 */
	public void popup(String title, String msg, int messageType) {
		if(isGuiEnabled()) {
			JOptionPane.showMessageDialog(getMainGui(), msg, title, messageType);
		} else {
			System.out.println(title + ": " + msg);
		}
	}
	
	/**¨
	 * Shortcut methode um eine Nachricht mit dem Typ Fehler zu generieren
	 * @param message
	 */
	public void error(String message) {
		popup("Fehler", message, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Aktiviert / Deaktiviert die Tabs je nachdem ob der Benutzer nun an- oder abgemeldet ist
	 * @param loggedIn
	 */
	public void setLoggedIn(boolean loggedIn) {
		if(loggedIn) {
			mainGui.deactivateTab("Login");
			mainGui.activateTab("Bestellen");
		} else {
			mainGui.deactivateTab("Bestellen");
			mainGui.activateTab("Login");
		}
		mainGui.recalculateSize();
	}

	/**
	 * @return the loginCtrl
	 */
	public LoginController getLoginCtrl() {
		return loginCtrl;
	}

	/**
	 * @param loginCtrl the loginCtrl to set
	 */
	public void setLoginCtrl(LoginController loginCtrl) {
		this.loginCtrl = loginCtrl;
	}

	/**
	 * @return the mainGui
	 */
	public MainGui getMainGui() {
		return mainGui;
	}

	/**
	 * @param mainGui the mainGui to set
	 */
	public void setMainGui(MainGui mainGui) {
		this.mainGui = mainGui;
	}
	
}
