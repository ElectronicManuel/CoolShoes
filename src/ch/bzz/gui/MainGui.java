package ch.bzz.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import ch.bzz.gui.login.LoginTab;

public class MainGui extends JFrame {
	
	private JTabbedPane tabs;
	
	private LoginTab loginTab;

	public MainGui() {
		initSettings();
		initComponents();
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initSettings() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("CoolShoes Bestellstatus");
	}
	
	private void initComponents() {
		tabs = new JTabbedPane();
		loginTab = new LoginTab();
		
		tabs.add("Login", loginTab);
		add(tabs);
	}

	public static void main(String[] args) {
		new MainGui();
	}
	
}
