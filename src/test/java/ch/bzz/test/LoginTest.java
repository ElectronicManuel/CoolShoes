package ch.bzz.test;

import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.Test;

import ch.bzz.beans.Bestellung;
import ch.bzz.controller.MainController;
import ch.bzz.dao.BestellungsDAO;
import ch.bzz.gui.BestellungsTab;
import junit.framework.TestCase;

public class LoginTest extends TestCase {
	
	@Test
	public void testLoginSuccess(){
		boolean success = new MainController().getLoginCtrl().login("MK", "abcd");
		assertEquals(true, success);	
	}
	
	@Test
	public void testLoginFail(){
		boolean success = new MainController().getLoginCtrl().login("MK", "ahbd");
		assertEquals(false, success);
		
	}
	
	@Test
	public void testAuftragAufbereiten() throws Exception {
		MainController mc = new MainController();
		mc.initGui();
		mc.getLoginCtrl().login("Emanuel", "abcd");
		
		BestellungsTab bestellTab = mc.getMainGui().getTab("Bestellen", BestellungsTab.class);
		
		bestellTab.get("statusInput", JComboBox.class).setSelectedIndex(1); // Ändere Bestellstatus in der Combobox
		
		Bestellung bestellung = (Bestellung) bestellTab.get("orderList", JList.class).getSelectedValue();
		String status = bestellTab.get("statusInput", JComboBox.class).getSelectedItem().toString();
		
		BestellungsDAO.setBestellStatus(bestellung, status);
		
		assertEquals("Auftrag aufbereiten", bestellung.getCurrentBestellStatus().getStatus());
	}
	
	@Test
	public void testTeilauftragVerspaetet() throws Exception {
		MainController mc = new MainController();
		mc.initGui();
		mc.getLoginCtrl().login("Emanuel", "abcd");
		
		BestellungsTab bestellTab = mc.getMainGui().getTab("Bestellen", BestellungsTab.class);
		
		bestellTab.get("statusInput", JComboBox.class).setSelectedIndex(2); // Ändere Bestellstatus in der Combobox
		
		Bestellung bestellung = (Bestellung) bestellTab.get("orderList", JList.class).getSelectedValue();
		String status = bestellTab.get("statusInput", JComboBox.class).getSelectedItem().toString();
		
		BestellungsDAO.setBestellStatus(bestellung, status);
		
		assertEquals("Teilauftrag verspätet", bestellung.getCurrentBestellStatus().getStatus());
	}
	
	@Test
	public void testAuftragVersandbereit() throws Exception {
		MainController mc = new MainController();
		mc.initGui();
		mc.getLoginCtrl().login("Emanuel", "abcd");
		
		BestellungsTab bestellTab = mc.getMainGui().getTab("Bestellen", BestellungsTab.class);
		
		bestellTab.get("statusInput", JComboBox.class).setSelectedIndex(3); // Ändere Bestellstatus in der Combobox
		
		Bestellung bestellung = (Bestellung) bestellTab.get("orderList", JList.class).getSelectedValue();
		String status = bestellTab.get("statusInput", JComboBox.class).getSelectedItem().toString();
		
		BestellungsDAO.setBestellStatus(bestellung, status);
		
		assertEquals("Auftrag versandbereit", bestellung.getCurrentBestellStatus().getStatus());
	}
	
	@Test
	public void testAuftragAbgeholt() throws Exception {
		MainController mc = new MainController();
		mc.initGui();
		mc.getLoginCtrl().login("Emanuel", "abcd");
		
		BestellungsTab bestellTab = mc.getMainGui().getTab("Bestellen", BestellungsTab.class);
		
		bestellTab.get("statusInput", JComboBox.class).setSelectedIndex(4); // Ändere Bestellstatus in der Combobox
		
		Bestellung bestellung = (Bestellung) bestellTab.get("orderList", JList.class).getSelectedValue();
		String status = bestellTab.get("statusInput", JComboBox.class).getSelectedItem().toString();
		
		BestellungsDAO.setBestellStatus(bestellung, status);
		
		assertEquals("Auftrag abgeholt", bestellung.getCurrentBestellStatus().getStatus());
	}
	
	@Test
	public void testAuftragGeliefert() throws Exception {
		MainController mc = new MainController();
		mc.initGui();
		mc.getLoginCtrl().login("Emanuel", "abcd");
		
		BestellungsTab bestellTab = mc.getMainGui().getTab("Bestellen", BestellungsTab.class);
		
		bestellTab.get("statusInput", JComboBox.class).setSelectedIndex(5); // Ändere Bestellstatus in der Combobox
		
		Bestellung bestellung = (Bestellung) bestellTab.get("orderList", JList.class).getSelectedValue();
		String status = bestellTab.get("statusInput", JComboBox.class).getSelectedItem().toString();
		
		BestellungsDAO.setBestellStatus(bestellung, status);
		
		assertEquals("Auftrag geliefert", bestellung.getCurrentBestellStatus().getStatus());
	}
	
	@Test
	public void testAuftragBestellt() throws Exception {
		MainController mc = new MainController();
		mc.initGui();
		mc.getLoginCtrl().login("Emanuel", "abcd");
		
		BestellungsTab bestellTab = mc.getMainGui().getTab("Bestellen", BestellungsTab.class);
		
		bestellTab.get("statusInput", JComboBox.class).setSelectedIndex(6); // Ändere Bestellstatus in der Combobox
		
		Bestellung bestellung = (Bestellung) bestellTab.get("orderList", JList.class).getSelectedValue();
		String status = bestellTab.get("statusInput", JComboBox.class).getSelectedItem().toString();
		
		BestellungsDAO.setBestellStatus(bestellung, status);
		
		assertEquals("Auftrag geliefert", bestellung.getCurrentBestellStatus().getStatus());
	}
	
	@Test
	public void testVermerkSetzen() throws Exception {
		MainController mc = new MainController();
		mc.initGui();
		mc.getLoginCtrl().login("Emanuel", "abcd");
		
		BestellungsTab bestellTab = mc.getMainGui().getTab("Bestellen", BestellungsTab.class);
		
		bestellTab.get("vermerkInput", JTextArea.class).setText("Hallo dies ist ein JUnit Test"); // Ändere Bestellstatus in der Combobox
		
		Bestellung bestellung = (Bestellung) bestellTab.get("orderList", JList.class).getSelectedValue();
		
		BestellungsDAO.save(bestellung);
		
		assertEquals("Hallo dies ist ein JUnit Test", bestellung.getVermerk());
	}
	
	@Test
	public void testDetailsAnzeigen(){
		MainController mc = new MainController();
		mc.initGui();
		mc.getLoginCtrl().login("MK", "abcd");
		
		BestellungsTab bestellTab = mc.getMainGui().getTab("Bestellen", BestellungsTab.class);
		Bestellung bestellung = (Bestellung) bestellTab.get("orderList", JList.class).getSelectedValue();
		
		//Kunde
		String kunde = bestellTab.get("kundeInput", JTextField.class).getText();
		assertEquals(bestellung.getKunde().getVorname() + bestellung.getKunde().getNachname(), kunde);
		
		//Mitarbeiter
		String mitarbeiter = bestellTab.get("mitarbeiterInput", JTextField.class).getText();
		assertEquals(bestellung.getMitarbeiter().getVorname() + bestellung.getMitarbeiter().getNachname(), mitarbeiter);
		
		//Bestellstaus
		String bestellstatus = bestellTab.get("statusInput", JComboBox.class).getSelectedItem().toString();
		assertEquals(bestellung.getCurrentBestellStatus(), bestellstatus);
		
		//Vermerk
		String vermerk = bestellTab.get("vermerkInput", JTextArea.class).getText();
		assertEquals(bestellung.getVermerk(), vermerk);
	}
	
	@Test
	public void testStatusSetzen() throws Exception{
		MainController mc = new MainController();
		mc.initGui();
		mc.getLoginCtrl().login("Emanuel", "abcd");
		
		BestellungsTab bestellTab = mc.getMainGui().getTab("Bestellen", BestellungsTab.class);
		
		bestellTab.get("statusInput", JComboBox.class).setSelectedIndex(1); // Ändere Bestellstatus in der Combobox
		
		Bestellung bestellung = (Bestellung) bestellTab.get("orderList", JList.class).getSelectedValue();
		String status = bestellTab.get("statusInput", JComboBox.class).getSelectedItem().toString();
		
		BestellungsDAO.setBestellStatus(bestellung, status);
		
		Date timestamp = bestellung.getCurrentBestellStatus().getGesetzt();
		
		assertEquals("Auftrag aufbereiten", bestellung.getCurrentBestellStatus().getStatus());
		assertEquals(timestamp, bestellung.getCurrentBestellStatus().getGesetzt());
	}
	
	@Test
	public void testHistoryAnschauen() throws Exception{
		
	}
}
