package ch.bzz.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ch.bzz.controller.MainController;

public class LoginTab extends JPanel {
	
	private JTextField username;
	private JPasswordField password;
	private JButton loginButton;
	private JProgressBar loading;
	
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
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 4;
		
		loading = new JProgressBar();
		loading.setIndeterminate(true);
		add(loading, c);
		loading.setVisible(false);
		
	}
	
	private void initListeners() {
		loginButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				final String name = username.getText();
				final String pw = password.getText();
				
				new Thread(new Runnable() {
					
					public void run() {
						MainController.getInstance().getLoginCtrl().login(name, pw);
						MainController.getInstance().getMainGui().recalculateSize();
					}
				}).start();
				
				loginButton.setEnabled(false);
				load(true);
				MainController.getInstance().getMainGui().recalculateSize();
			}
			
		});
	}
	
	public void load(boolean toLoad) {
		loading.setVisible(toLoad);
	}

	public JButton getLoginButton() {
		return loginButton;
	}

}
