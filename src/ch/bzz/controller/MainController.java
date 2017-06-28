package ch.bzz.controller;

import javax.swing.JOptionPane;

import ch.bzz.gui.BestellungsTab;
import ch.bzz.gui.LoginTab;
import ch.bzz.gui.MainGui;

public class MainController {
	
	// Singleton
	private final static MainController instance = new MainController();
	public static MainController getInstance() {
		return instance;
	}
	
	// Controller Classes
	private LoginController loginCtrl;
	
	// Gui Classes
	private MainGui mainGui;
	
	private MainController() {
		System.out.println("MAIN CTRL INIT");
		
		initControllers();
		
		mainGui = new MainGui();
	}
	
	private void initControllers() {
		loginCtrl = new LoginController();
	}
	
	public void popup(String title, String msg, int messageType) {
		JOptionPane.showMessageDialog(getMainGui(), msg, title, messageType);
	}
	
	public void error(String message) {
		popup("Fehler", message, JOptionPane.ERROR_MESSAGE);
	}

	public LoginController getLoginCtrl() {
		return loginCtrl;
	}

	public MainGui getMainGui() {
		return mainGui;
	}

	public static void main(String[] args) {
	}

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
	
}
