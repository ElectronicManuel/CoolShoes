package ch.bzz.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ToolTipManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ch.bzz.beans.BestellStatus;
import ch.bzz.beans.Bestellung;
import ch.bzz.beans.Mitarbeiter;
import ch.bzz.controller.MainController;
import ch.bzz.dao.BestellungsDAO;
import ch.bzz.util.GUIUtil;

/**
 * Dieser Tab wird verwendet um Bestellungen einzusehen oder zu bearbeiten
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */
public class BestellungsTab extends CoolTab implements ListSelectionListener, ActionListener {

	/**
	 * Siehe Cooltab
	 */
	@Override
	protected void initBaseComponents() {
		ToolTipManager.sharedInstance().setDismissDelay(1000000);
		
		setLayout(new BorderLayout());
		
		ArrayList<String> allStatus = new ArrayList<String>();
		allStatus.add("Nicht definiert");
		allStatus.add("Auftrag aufbereiten");
		allStatus.add("Teilauftrag verspätet");
		allStatus.add("Auftrag versandbereit");
		allStatus.add("Auftrag abgeholt");
		allStatus.add("Auftrag geliefert");
		allStatus.add("Auftrag bestellt");

		/* Bestellungsliste */
		JList<Bestellung> orderList = new JList<Bestellung>();
		orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderList.addListSelectionListener(this);
		
		addComp("orderList", orderList);
		add(GUIUtil.scrollify(orderList, new Dimension(20, 200)), BorderLayout.WEST);
		
		/* Detaillierte Anzeige */
		JPanel details = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 5);
		
		// Kunde
		details.add(new JLabel("Kunde"), c);
		c.gridx = 1;
		addComp("kundeInput", new JTextField(40));
		get("kundeInput").setEnabled(false);
		details.add(get("kundeInput"), c);
		c.gridx = 0;
		c.gridy++;
		
		// Mitarbeiter
		details.add(new JLabel("Mitarbeiter"), c);
		c.gridx = 1;
		addComp("mitarbeiterInput", new JTextField(40));
		get("mitarbeiterInput").setEnabled(false);
		details.add(get("mitarbeiterInput"), c);
		c.gridx = 0;
		c.gridy++;
		
		// Status
		
		c.anchor = GridBagConstraints.NORTH;
		details.add(new JLabel("Bestellstatus"), c);
		c.gridx = 1;
		JComboBox<Object> statusAuswahl = new JComboBox<>();
		statusAuswahl.setPreferredSize(new Dimension(325, 20));
		statusAuswahl.setModel(new DefaultComboBoxModel<Object>(allStatus.toArray()));
		addComp("statusInput", statusAuswahl);
		get("statusInput").setEnabled(false);
		details.add(get("statusInput"), c);
		c.gridx = 0;
		c.gridy++;
		
		// Vermerk
		details.add(new JLabel("Vermerk"), c);
		c.gridx = 1;
		JTextArea vermerkArea = new JTextArea(10, 40);
		vermerkArea.setEditable(true);
		vermerkArea.setLineWrap(true);
		vermerkArea.setWrapStyleWord(true);
		addComp("vermerkInput", vermerkArea);
		get("vermerkInput").setEnabled(false);
		details.add(GUIUtil.scrollify(vermerkArea, new Dimension(325, 300)), c);
		c.gridx = 0;
		c.gridy++;
		
		// Speichern Knopf
		addComp("saveButton", new JButton("Änderungen speichern"));
		get("saveButton", JButton.class).addActionListener(this);
		details.add(get("saveButton"), c);
		c.gridx = 0;
		c.gridy++;
		
		// History Label
		addComp("historyLabel", new JLabel("<html><span style=\"color:blue;\">History anzeigen</span></html>"));
		
		details.add(get("historyLabel"), c);
		c.gridx = 0;
		c.gridy++;
		
		add(details, BorderLayout.CENTER);
		
		/* Abmelden Knopf */
		addComp("logoutButton", new JButton("Abmelden"));
		get("logoutButton", JButton.class).addActionListener(this);
		add(GUIUtil.wrap(get("logoutButton")), BorderLayout.SOUTH);
		
	}
	
	/**
	 * Setzt den Tooltip des History anzeigen Labels auf die Aktuelle history
	 */
	private void updateHistory() {
		Bestellung bestellung = (Bestellung) get("orderList", JList.class).getSelectedValue();
		
		String tooltip = "<html>";
		
		int i = 0;
		
		for(BestellStatus bestellStatus : bestellung.getBestellStati()) {
			tooltip += (i > 0 ? "<br>" : "") + bestellStatus.getStatus() + " " + " - " + bestellStatus.getGesetzt().toString();
			i++;
		}
		
		tooltip += "</html>";
		
		get("historyLabel", JLabel.class).setToolTipText(tooltip);
	}

	/**
	 * Siehe Cooltab
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void loadDynamicContent() {
		Bestellung[] listData = new Bestellung[]{};
		
		// Lese Bestellungen aus der Datenbank aus
		try {
			listData = BestellungsDAO.getBestellungenByUser(MainController.getInstance().getLoginCtrl().getUser()).toArray(new Bestellung[]{});
		} catch(Exception ex) {
			MainController.getInstance().error("Ein Fehler ist aufgetreten");
		}
		((JList<Bestellung>)get("orderList", JList.class)).setListData(listData);
		if(listData.length > 0) {
			((JList<Bestellung>)get("orderList", JList.class)).setSelectedIndex(0);
			
			updateHistory();
		}
	
		if(MainController.getInstance().getLoginCtrl().getUser() instanceof Mitarbeiter) {
			get("statusInput", JComboBox.class).setEnabled(true);
			get("vermerkInput", JTextArea.class).setEnabled(true);
		} else {
			get("saveButton").setVisible(false);
		}
		
		updateDetails();
	}


	/**
	 * Siehe Cooltab
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void unloadDynamicContent() {
		get("statusInput", JComboBox.class).setEnabled(false);
		get("vermerkInput", JTextArea.class).setEnabled(false);
		get("orderList", JList.class).setListData(new Bestellung[]{});
		get("historyLabel", JLabel.class).setToolTipText("");
	}

	/**
	 * Siehe Cooltab
	 */
	@Override
	public JButton getDefaultButton() {
		return null;
	}

	/**
	 * Siehe Cooltab
	 */
	@Override
	public JComponent getDefaultFocus() {
		return null;
	}

	/**
	 * Passt die Werte innerhalb der Detaillierten Anzeige der aktuell ausgewählten Bestellung an
	 */
	private void updateDetails() {
		Bestellung current = ((Bestellung)get("orderList", JList.class).getSelectedValue());
		if(current != null) {
			get("kundeInput", JTextField.class).setText(current.getKunde().toString());
			get("mitarbeiterInput", JTextField.class).setText(current.getMitarbeiter().getVorname() + " " + current.getMitarbeiter().getNachname());
			if(current.getCurrentBestellStatus() != null) {
				get("statusInput", JComboBox.class).setSelectedItem(current.getCurrentBestellStatus().getStatus());
			}
			get("vermerkInput", JTextArea.class).setText(current.getVermerk());
			
			updateHistory();
		} else {
			get("kundeInput", JTextField.class).setText("");
			get("mitarbeiterInput", JTextField.class).setText("");
			get("statusInput", JComboBox.class).setSelectedIndex(0);
			get("vermerkInput", JTextArea.class).setText("");
			
			get("historyLabel", JLabel.class).setToolTipText("");
		}
	}
	
	/*
	 * Action Listener für Änderungen speichern und abmelden
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == get("saveButton")) {
			if(MainController.getInstance().getLoginCtrl().getUser() instanceof Mitarbeiter) {
				new Thread(new Runnable() {				
					public void run() {
						try {
							Bestellung toSave = (Bestellung)get("orderList", JList.class).getSelectedValue();
							
							String status = get("statusInput", JComboBox.class).getSelectedItem().toString();
							String vermerk = get("vermerkInput", JTextArea.class).getText().toString();
							BestellungsDAO.setBestellStatus(toSave, status);
							
							toSave.setVermerk(vermerk);
							
							BestellungsDAO.save(toSave);
							
							updateDetails();
							
							MainController.getInstance().popup("Gespeichert", "Änderungen wurden erfolgreich gespeichert", JOptionPane.INFORMATION_MESSAGE);
						} catch(Exception ex) {
							MainController.getInstance().error("Ein Fehler ist aufgetreten");
						}
						get("saveButton").setEnabled(true);
					}
				}).start();
				
				get("saveButton").setEnabled(false);
			}
		} else if(e.getSource() == get("logoutButton")) {
			MainController.getInstance().setLoggedIn(false);
		}
	}
	
	/**
	 * Change Listener für die Bestellungsliste
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource() == get("orderList")) {
			updateDetails();
		}
	}
	
}