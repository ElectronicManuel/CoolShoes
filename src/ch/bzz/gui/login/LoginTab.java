package ch.bzz.gui.login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ch.bzz.controller.MainController;

public class LoginTab extends JPanel {
	
	private JTextField username;
	private JPasswordField password;
	private JButton loginButton;
	
	public LoginTab() {
		initSettings();
		initComponents();
		initListeners();
	}
	
	private void initSettings() {
		setLayout(new GridBagLayout());
	}
	
	private void initComponents() {
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		
		add(new JLabel("Benutzername"), c);
		c.gridx = 1;
		
		username = new JTextField(40);
		add(username, c);
		c.gridy++;
		c.gridx = 0;
		
		add(new JLabel("Passwort"), c);
		c.gridx = 1;
		
		password = new JPasswordField(40);
		add(password, c);
		c.gridy++;
		c.gridx = 0;
		
		loginButton = new JButton("Login");
		add(loginButton, c);
	}
	
	private void initListeners() {
		loginButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String name = username.getText();
				String pw = password.getPassword().toString();
				
				MainController.getInstance().getLoginCtrl().login(name, pw);
			}
			
		});
	}

}
