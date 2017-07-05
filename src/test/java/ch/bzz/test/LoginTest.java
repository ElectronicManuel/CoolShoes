package ch.bzz.test;

import javax.swing.JComboBox;
import javax.swing.JList;

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
	public void testSetAuftragAufbereiten() throws Exception {
		MainController mc = new MainController();
		mc.initGui();
		mc.getLoginCtrl().login("MK", "abcd");
		
		BestellungsTab bestellTab = mc.getMainGui().getTab("Bestellen", BestellungsTab.class);
		
		bestellTab.get("statusInput", JComboBox.class).setSelectedIndex(1); // Ändere Bestellstatus in der Combobox
		
		Bestellung bestellung = (Bestellung) bestellTab.get("orderList", JList.class).getSelectedValue();
		String status = bestellTab.get("statusInput", JComboBox.class).getSelectedItem().toString();
		
		BestellungsDAO.setBestellStatus(bestellung, status);
		
		assertEquals("Auftrag aufbereiten", bestellung.getCurrentBestellStatus().getStatus());
	}
}
