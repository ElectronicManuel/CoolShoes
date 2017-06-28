package ch.bzz.gui;

import java.awt.BorderLayout;
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
import ch.bzz.beans.Kunde;
import ch.bzz.beans.Mitarbeiter;
import ch.bzz.controller.MainController;
import ch.bzz.dao.BestellungsDAO;
import ch.bzz.util.GUIUtil;

public class BestellungsTab extends CoolTab {
	
	public BestellungsTab() {
		initSettings();
		initComponents();
		
		updateDetails();
	}

	private void initSettings() {
		setLayout(new BorderLayout());
	}
	
	private void initComponents() {
		JList<Bestellung> bestellungsListe = new JList<Bestellung>();
		Bestellung[] listData = new Bestellung[]{};
		
		// Lese Bestellungen aus der Datenbank aus
		try {
			listData = BestellungsDAO.getBestellungenByUser(MainController.getInstance().getLoginCtrl().getUser()).toArray(new Bestellung[]{});
		} catch(Exception ex) {
			MainController.getInstance().error("Ein Fehler ist aufgetreten");
		}
		bestellungsListe.setListData(listData);
		bestellungsListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		if(listData.length > 0) bestellungsListe.setSelectedIndex(0);
		
		bestellungsListe.addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {
				updateDetails();
			}
			
		});
		
		addComp("orderList", bestellungsListe);
		add(bestellungsListe, BorderLayout.WEST);
		
		addComp("logoutButton", new JButton("Abmelden"));
		
		get("logoutButton", JButton.class).addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().setLoggedIn(false);
			}
			
		});
		add(GUIUtil.wrap(get("logoutButton")), BorderLayout.SOUTH);
		
		JPanel details = new JPanel();
		
		addComp("kundenLabel", new JLabel(""));
		addComp("mitarbeiterLabel", new JLabel(""));
		details.add(get("kundenLabel"));
		details.add(get("mitarbeiterLabel"));
		
		if(MainController.getInstance().getLoginCtrl().getUser() instanceof Kunde) {
			addComp("statusLabel", new JLabel(""));
			details.add(get("statusLabel"));
		} else if(MainController.getInstance().getLoginCtrl().getUser() instanceof Mitarbeiter) {
			addComp("statusInput", new JTextField(30));
			details.add(get("statusInput"));
			addComp("saveButton", new JButton("Änderungen speichern"));
			get("saveButton", JButton.class).addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
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
			});
			add(get("saveButton"), BorderLayout.EAST);
		}
		
		addComp("detailPanel", details);
		add(details, BorderLayout.CENTER);
	}
	
	private void updateDetails() {
		Bestellung current = ((Bestellung)get("orderList", JList.class).getSelectedValue());
		if(current != null) {
			get("kundenLabel", JLabel.class).setText(current.getKunde().getEmail());
			get("mitarbeiterLabel", JLabel.class).setText(current.getMitarbeiter().getVorname() + " " + current.getMitarbeiter().getNachname());
			
			if(MainController.getInstance().getLoginCtrl().getUser() instanceof Kunde) {
				get("statusLabel", JLabel.class).setText(current.getBestellStatus().getStatus());
			} else if(MainController.getInstance().getLoginCtrl().getUser() instanceof Mitarbeiter) {
				get("statusInput", JTextField.class).setText(current.getBestellStatus().getStatus());
				get("statusInput", JTextField.class).setVisible(true);
			}
			
		} else {
			get("kundenLabel", JLabel.class).setText("");
			get("mitarbeiterLabel", JLabel.class).setText("");
			if(has("statusLabel")) get("statusLabel", JLabel.class).setText("");
			if(has("statusInput")) get("statusInput").setVisible(false);
		}
		
		repaint();
	}

	@Override
	public JButton getDefaultButton() {
		return null;
	}

	@Override
	public JComponent getDefaultFocus() {
		return null;
	}
	
}
