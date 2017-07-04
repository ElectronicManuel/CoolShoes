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
	
	public void recalculateSize() {
		Dimension size = getMinimumSize();
		setMinimumSize(new Dimension(0, 0));
		setSize(size);
		revalidate();
		pack();
		repaint();
		setMinimumSize(getSize());
	}
	
	public void addTab(String name, CoolTab tab) {
		tabList.put(name, tab);
		tab.initBaseComponents();
	}
	
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
	
	public void removeTabWithTitle(String tabTitleToRemove) {
	    for (int i = 0; i < tabs.getTabCount(); i++) {
	        String tabTitle = tabs.getTitleAt(i);
	        if (tabTitle.equals(tabTitleToRemove)) {
	        	tabs.remove(i);
	            break;
	        }
	    }
	}
	
	public <T extends CoolTab> T getTab(String name, Class<T> clazz) {
		return clazz.cast(tabList.get(name));
	}
	
}
