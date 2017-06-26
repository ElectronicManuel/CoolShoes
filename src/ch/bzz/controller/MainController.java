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
		initControllers();
		
		mainGui = new MainGui();
	}
	
	private void initControllers() {
		loginCtrl = new LoginController();
	}
	
	public void popup(String title, String msg, int messageType) {
		JOptionPane.showMessageDialog(getMainGui(), msg, title, messageType);
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
			mainGui.setBestellungsTab(new BestellungsTab());
			mainGui.setLoginTab(null);
			mainGui.removeTabWithTitle("Login");
			mainGui.getTabs().add("Bestellungen", mainGui.getBestellungsTab());
		} else {
			mainGui.setBestellungsTab(null);
			mainGui.setLoginTab(new LoginTab());
			mainGui.removeTabWithTitle("Bestellungen");
			mainGui.getTabs().add("Login", mainGui.getLoginTab());
		}
		mainGui.pack();
	}
	
}
