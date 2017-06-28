package ch.bzz.gui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class CoolTab extends JPanel {
	
	private Map<String, JComponent> componentList;
	
	public abstract JButton getDefaultButton();
	public abstract JComponent getDefaultFocus();
	
	public CoolTab() {
		componentList = new HashMap<String, JComponent>();
	}
	
	public void addComp(String name, JComponent comp) {
		componentList.put(name, comp);
	}
	
	public JComponent get(String name) {
		if(componentList.containsKey(name)) return componentList.get(name);
		return null;
	}
	
	public <T extends JComponent> T get(String name, Class<T> clazz) {
		if(componentList.containsKey(name)) return clazz.cast(componentList.get(name));
		return null;
	}
	
	public boolean has(String name) {
		return get(name) != null;
	}
	
	public void activate() {
		for(String name : componentList.keySet()) {
			get(name).setVisible(true);
		}
	}
	
	public void deactivate() {
		for(String name : componentList.keySet()) {
			get(name).setVisible(false);
		}
	}

}
