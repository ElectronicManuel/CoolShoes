package ch.bzz.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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

public class BestellungsTab extends JPanel {
	
	private JList<Bestellung> bestellungsListe;
	
	private JLabel kundenAnzeige;
	private JLabel mitarbeiterAnzeige;
	private JLabel bestellStatus;
	
	private JTextField statusEdit;
	
	private JButton saveButton;
	private JButton logoutButton;
	
	public BestellungsTab() {
		initSettings();
		initComponents();
		
		updateDetails();
	}

	private void initSettings() {
		setLayout(new BorderLayout());
	}
	
	private void initComponents() {
		bestellungsListe = new JList<Bestellung>();
		Bestellung[] listData = new Bestellung[]{};
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
		
		add(bestellungsListe, BorderLayout.WEST);
		
		logoutButton = new JButton("Abmelden");
		JPanel wrapper = new JPanel();
		wrapper.add(logoutButton);
		logoutButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().setLoggedIn(false);
			}
		});
		add(wrapper, BorderLayout.SOUTH);
		JPanel details = new JPanel();
		
		kundenAnzeige = new JLabel("");
		mitarbeiterAnzeige = new JLabel("");
		details.add(kundenAnzeige);
		details.add(mitarbeiterAnzeige);
		
		if(MainController.getInstance().getLoginCtrl().getUser() instanceof Kunde) {
			bestellStatus = new JLabel("");
			details.add(bestellStatus);
		} else if(MainController.getInstance().getLoginCtrl().getUser() instanceof Mitarbeiter) {
			statusEdit = new JTextField(30);
			details.add(statusEdit);
			statusEdit.setVisible(true);
			saveButton = new JButton("Änderungen speichern");
			saveButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					new Thread(new Runnable() {
						
						public void run() {
							try {
								bestellungsListe.getSelectedValue().getBestellStatus().setStatus(statusEdit.getText());
								BestellungsDAO.save(bestellungsListe.getSelectedValue());
								MainController.getInstance().popup("Gespeichert", "Änderungen wurden erfolgreich gespeichert", JOptionPane.INFORMATION_MESSAGE);
							} catch(Exception ex) {
								MainController.getInstance().error("Ein Fehler ist aufgetreten");
							}
							saveButton.setEnabled(true);
						}
					}).start();
					
					saveButton.setEnabled(false);
				}
			});
			add(saveButton, BorderLayout.EAST);
		}
		
		add(details, BorderLayout.CENTER);
	}
	
	private void updateDetails() {
		Bestellung current = bestellungsListe.getSelectedValue();
		if(current != null) {
			kundenAnzeige.setText(current.getKunde().getEmail());
			mitarbeiterAnzeige.setText(current.getMitarbeiter().getVorname() + " " + current.getMitarbeiter().getNachname());
			
			if(MainController.getInstance().getLoginCtrl().getUser() instanceof Kunde) {
				bestellStatus.setText(current.getBestellStatus().getStatus());
			} else if(MainController.getInstance().getLoginCtrl().getUser() instanceof Mitarbeiter) {
				statusEdit.setText(current.getBestellStatus().getStatus());
				statusEdit.setVisible(true);
			}
			
		} else {
			kundenAnzeige.setText("");
			mitarbeiterAnzeige.setText("");
			bestellStatus.setText("");
			statusEdit.setVisible(false);
		}
		
		repaint();
	}
	
}
