package ch.bzz.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class MainGui extends JFrame {
	
	private JTabbedPane tabs;
	
	private LoginTab loginTab;
	private BestellungsTab bestellungsTab;

	public MainGui() {
		initSettings();
		initComponents();
		
		recalculateSize();
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
	
	public void recalculateSize() {
		revalidate();
		pack();
		repaint();
		setMinimumSize(getSize());
	}
	
	public void removeTabWithTitle(String tabTitleToRemove) {
	    for (int i = 0; i < tabs.getTabCount(); i++) {
	        String tabTitle = tabs.getTitleAt(i);
	        if (tabTitle.equals(tabTitleToRemove)) {
	        	tabs.remove(i);
	            break;
	        }
	    }
	}
	
	public JTabbedPane getTabs() {
		return tabs;
	}

	public LoginTab getLoginTab() {
		return loginTab;
	}

	public void setLoginTab(LoginTab loginTab) {
		this.loginTab = loginTab;
	}

	public BestellungsTab getBestellungsTab() {
		return bestellungsTab;
	}

	public void setBestellungsTab(BestellungsTab bestellungsTab) {
		this.bestellungsTab = bestellungsTab;
	}

	public static void main(String[] args) {
		new MainGui();
	}
	
}
