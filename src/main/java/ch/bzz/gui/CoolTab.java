package ch.bzz.gui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Diese Klasse ist die gemeinsame Oberklasse aller Tabs und sie bietet eine Komponentenliste um Komponenten zu speichern und auszulesen ohne immer eine Variabel zu erstellen
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */
public abstract class CoolTab extends JPanel {
	
	/**
	 *  Die Liste aller Komponenten
	 */
	private Map<String, JComponent> componentList;
	
	/**
	 *  Zeigt an ob der Tab gerade im MainGui aktiv ist
	 */
	private boolean active = false;
	
	/**
	 * Wird aufgerufen wenn der Tab durch das MainGui aktiviert wird, löst die Ausführung der abstrakten Methode loadDynamicContent aus
	 */
	public void activate() {
		for(String name : componentList.keySet()) {
			get(name).setVisible(true);
		}
		loadDynamicContent();
		active = true;
	}
	
	/**
	 * Wird aufgerufen wenn der Tab durch das MainGui deaktiviert wird, löst die Ausführung der abstrakten Methode unloadDynamicContent aus
	 */
	public void deactivate() {
		for(String name : componentList.keySet()) {
			get(name).setVisible(false);
		}
		unloadDynamicContent();
		active = false;
	}
	
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Dient dazu statische Komponenten zu initialisieren, solange sich dabei zwischen aktivierungen und deaktivierungen nichts ändert
	 */
	protected abstract void initBaseComponents();
	
	/**
	 * Dient dazu dynamische werte zu setzen (Die Bestellungen des angemeldeten Kunden in die Liste zu schreiben)
	 */
	protected abstract void loadDynamicContent();
	
	/**
	 * Dient dazu die dynamischen Werte wieder zu entfernen um Konsitent zu gewährleisten
	 */
	protected abstract void unloadDynamicContent();
	
	/**
	 * Wird vom MainGui aufgerufen und als DefaultButton des JPanels gesetzt
	 * @return der JButton welcher der DefaultButton sein soll
	 */
	public abstract JButton getDefaultButton();
	
	/**
	 * Wird vom MainGui aufgerufen und erhält den Fokus des Fensters
	 * @return
	 */
	public abstract JComponent getDefaultFocus();
	
	public CoolTab() {
		componentList = new HashMap<String, JComponent>();
	}
	
	/**
	 * Speichert einen JComponent unter einem bestimmten Namen
	 * @param name
	 * @param comp Komponent
	 */
	public void addComp(String name, JComponent comp) {
		componentList.put(name, comp);
	}
	
	/**
	 * Gibt den JComponent mit einem bestimmten Namen zurück, muss allerdings anschliessen noch gecastet werden falls eine Unterklasse verwendet wird
	 * @param name
	 * @return der JComponent mit dem entsprechenden Namen
	 */
	public JComponent get(String name) {
		if(componentList.containsKey(name)) return componentList.get(name);
		return null;
	}
	
	/**
	 * Gibt den JComponent mit einem bestimmten Namen zurück, castet diesen aber vorher zu dem mitgegebenen Typen
	 * @param name
	 * @param clazz Typ des Komponenten
	 * @return der JComponent mit dem entsprechenden Namen gecastet zu dem mitgegebenen Typen
	 */
	public <T extends JComponent> T get(String name, Class<T> clazz) {
		if(componentList.containsKey(name)) return clazz.cast(componentList.get(name));
		return null;
	}
	
	/**
	 * Überprüft ob ein Komponent mit einem bestimmten Namen in der Liste enthalten ist
	 * @param name
	 * @return true wenn ja, false wenn nein
	 */
	public boolean has(String name) {
		return get(name) != null;
	}
}
