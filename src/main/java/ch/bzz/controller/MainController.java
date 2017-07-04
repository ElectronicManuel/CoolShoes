package ch.bzz.controller;

import javax.swing.JOptionPane;

import ch.bzz.gui.MainGui;

public class MainController {
	
	// Singleton
	private static MainController instance;
	public static MainController getInstance() {
		return instance;
	}
	
	// Controller Classes
	private LoginController loginCtrl;
	
	// Gui Classes
	private MainGui mainGui;
	
	public MainController() {
		instance = this;
		initControllers();
	}
	
	public void initGui() {
		mainGui = new MainGui();
	}
	
	private void initControllers() {
		loginCtrl = new LoginController();
	}
	
	public static boolean isGuiEnabled() {
		return getInstance().mainGui != null;
	}
	
	public void popup(String title, String msg, int messageType) {
		if(isGuiEnabled()) {
			JOptionPane.showMessageDialog(getMainGui(), msg, title, messageType);
		} else {
			System.out.println(title + ": " + msg);
		}
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
		new MainController();
		MainController.getInstance().initGui();
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
