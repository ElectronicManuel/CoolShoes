package ch.bzz.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ch.bzz.beans.Bestellung;
import ch.bzz.beans.Mitarbeiter;
import ch.bzz.controller.MainController;
import ch.bzz.dao.BestellungsDAO;
import ch.bzz.util.GUIUtil;

public class BestellungsTab extends CoolTab implements ListSelectionListener, ActionListener {

	@Override
	protected void initBaseComponents() {
		setLayout(new BorderLayout());
		
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
		details.add(new JLabel("Bestellstatus"), c);
		c.gridx = 1;
		addComp("statusInput", new JTextField(40));
		get("statusInput").setEnabled(false);
		details.add(get("statusInput"), c);
		c.gridx = 0;
		c.gridy++;
		
		// Speichern Knopf
		addComp("saveButton", new JButton("Änderungen speichern"));
		get("saveButton", JButton.class).addActionListener(this);
		details.add(get("saveButton"), c);
		c.gridx = 0;
		c.gridy++;
		
		add(details, BorderLayout.CENTER);
		
		/* Abmelden Knopf */
		addComp("logoutButton", new JButton("Abmelden"));
		get("logoutButton", JButton.class).addActionListener(this);
		add(GUIUtil.wrap(get("logoutButton")), BorderLayout.SOUTH);
		
	}

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
		if(listData.length > 0) ((JList<Bestellung>)get("orderList", JList.class)).setSelectedIndex(0);
	
		if(MainController.getInstance().getLoginCtrl().getUser() instanceof Mitarbeiter) {
			get("statusInput", JTextField.class).setEnabled(true);
		} else {
			get("saveButton").setVisible(false);
		}
		
		updateDetails();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void unloadDynamicContent() {
		get("statusInput", JTextField.class).setEnabled(false);
		get("orderList", JList.class).setListData(new Bestellung[]{});
	}

	@Override
	public JButton getDefaultButton() {
		return null;
	}

	@Override
	public JComponent getDefaultFocus() {
		return null;
	}

	private void updateDetails() {
		Bestellung current = ((Bestellung)get("orderList", JList.class).getSelectedValue());
		if(current != null) {
			get("kundeInput", JTextField.class).setText(current.getKunde().getEmail());
			get("mitarbeiterInput", JTextField.class).setText(current.getMitarbeiter().getVorname() + " " + current.getMitarbeiter().getNachname());
			get("statusInput", JTextField.class).setText(current.getBestellStatus().getStatus());
		} else {
			get("kundeInput", JTextField.class).setText("");
			get("mitarbeiterInput", JTextField.class).setText("");
			get("statusInput", JTextField.class).setText("");
		}
	}
	
	/*
	 * Event Listeners
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == get("saveButton")) {
			if(MainController.getInstance().getLoginCtrl().getUser() instanceof Mitarbeiter) {
				new Thread(new Runnable() {
					
					public void run() {
						try {
							((Bestellung)get("orderList", JList.class).getSelectedValue()).getBestellStatus().setStatus(get("statusInput", JTextField.class).getText());
							BestellungsDAO.save(((Bestellung)get("orderList", JList.class).getSelectedValue()));
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
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource() == get("orderList")) {
			updateDetails();
		}
	}
	
}