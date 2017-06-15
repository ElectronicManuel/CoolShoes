package ch.bzz.controller;

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
		
		mainGui = new MainGui();
		initControllers();
	}
	
	private void initControllers() {
		loginCtrl = new LoginController();
	}

	public LoginController getLoginCtrl() {
		return loginCtrl;
	}

	public MainGui getMainGui() {
		return mainGui;
	}

}
