package ch.bzz.gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Dies ist das JFrame welches auf dem Bildschirm angezeigt wird, es enthält ein JTabbgedPane welches wiederum die verschieden Tabs (Login oder Bestellen) enthält
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */
public class MainGui extends JFrame {
	
	private JTabbedPane tabs;
	
	private List<String> currentTabs;
	
	private Map<String, CoolTab> tabList;
	
	public MainGui() {
		initSettings();
		initComponents();
		
		recalculateSize();
		setLocationRelativeTo(null);
		setVisible(true);
		
		activateTab("Login");
	}

	/**
	 * Setzt allgemeine Einstellungen
	 */
	private void initSettings() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("CoolShoes Bestellstatus");
	}
	
	/*
	 * Initialisiert die Komponenten
	 */
	private void initComponents() {
		currentTabs = new ArrayList<String>();
		tabList = new HashMap<String, CoolTab>();
		tabs = new JTabbedPane();
		
		tabs.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				recalculateSize();
			}
		});
		
		// initialisiere tabs
		addTab("Login", new LoginTab());
		addTab("Bestellen", new BestellungsTab());
		
		
		add(tabs);
	}
	
	/**
	 * Berechnet die mindestgrösse des GUI und lässt eine verkleinerung kleiner als diese grösse nicht zu, wird nach jeder Änderung des Inhaltes aufgerufen
	 */
	public void recalculateSize() {
		Dimension size = getMinimumSize();
		setMinimumSize(new Dimension(0, 0));
		setSize(size);
		revalidate();
		pack();
		repaint();
		setMinimumSize(getSize());
	}
	
	/**
	 * Fügt einen Tab der Tabliste hinzu
	 * @param name
	 * @param tab
	 */
	public void addTab(String name, CoolTab tab) {
		tabList.put(name, tab);
		tab.initBaseComponents();
	}
	
	/**
	 * Aktiviert einen Tab (hebt diesen in den Vordergrund, fügt ihn der JTabbedPane hinzu)
	 * @param name
	 */
	public void activateTab(String name) {
		if(tabList.containsKey(name)) {
			if(!currentTabs.contains(name)) {
				CoolTab toActivate = tabList.get(name);
				toActivate.activate();
				tabs.add(name, toActivate);
				currentTabs.add(name);
				
				// Default Werte
				if(toActivate.getDefaultButton() != null) getRootPane().setDefaultButton(toActivate.getDefaultButton());
				if(toActivate.getDefaultFocus() != null) toActivate.getDefaultFocus().requestFocusInWindow();
				
				recalculateSize();
			}
			else {
				System.err.println("Tab '" + name + "' ist bereits aktiv");
			}
		} else {
			System.err.println("Tab mit Namen '" + name + "' konnte nicht gefunden werden");
		}
	}
	
	/**
	 * Deaktiviert einen Tab (schiebt ihn in den Hintergrund, löscht ihn aus der JTabbedPane)
	 * @param name
	 */
	public void deactivateTab(String name) {
		if(tabList.containsKey(name)) {
			if(currentTabs.contains(name)) {
				CoolTab toDeactivate = tabList.get(name);
				toDeactivate.deactivate();
				removeTabWithTitle(name);
				currentTabs.remove(name);
				
				recalculateSize();
			}
			else {
				System.err.println("Tab '" + name + "' ist nicht aktiv");
			}
		} else {
			System.err.println("Tab mit Namen '" + name + "' konnte nicht gefunden werden");
		}
	}
	
	/**
	 * Löscht einen Tab mit einem bestimmten Titel aus der JTabbedPane
	 * @param tabTitleToRemove
	 */
	private void removeTabWithTitle(String tabTitleToRemove) {
	    for (int i = 0; i < tabs.getTabCount(); i++) {
	        String tabTitle = tabs.getTitleAt(i);
	        if (tabTitle.equals(tabTitleToRemove)) {
	        	tabs.remove(i);
	            break;
	        }
	    }
	}
	
	/**
	 * Diese Methode erleichtert den Zugriff auf Tabs, denn sie gibt den Tab bereits gecastet zurück
	 * 
	 * @param name
	 * @param clazz Typ des Tabs
	 * @return Tab zu dem Typ gecastet
	 * @throws ClassCastException wenn der Tab nicht gecastet werden kann
	 */
	public <T extends CoolTab> T getTab(String name, Class<T> clazz) {
		return clazz.cast(tabList.get(name));
	}
	
}
