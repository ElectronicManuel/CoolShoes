package ch.bzz.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import ch.bzz.controller.MainController;

public class LoginTab extends CoolTab {
	
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
		
		addComp("usernameLabel", new JLabel("Benutzername"));
		
		add(get("usernameLabel"), c);
		c.gridx = 1;
		
		addComp("usernameInput", new JTextField(40));
		add(get("usernameInput"), c);
		c.gridy++;
		c.gridx = 0;
		
		addComp("passwordLabel", new JLabel("Passwort"));
		add(get("passwordLabel"), c);
		c.gridx = 1;
		
		addComp("passwordInput", new JPasswordField(40));
		add(get("passwordInput"), c);
		c.gridy++;
		c.gridx = 0;

		addComp("loginButton", new JButton("Anmelden"));
		add(get("loginButton"), c);
		c.gridy++;
		c.gridx = 0;
		c.gridwidth = 4;
		
		JProgressBar loading = new JProgressBar();
		loading.setIndeterminate(true);
		addComp("loading", new JProgressBar());
		add(loading, c);
	}
	
	private void initListeners() {
		get("loginButton", JButton.class).addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				final String name = get("usernameInput", JTextField.class).getText();
				final String pw = get("passwordInput", JPasswordField.class).getText();
				
				new Thread(new Runnable() {
					
					public void run() {
						MainController.getInstance().getLoginCtrl().login(name, pw);
						load(false);
						get("loginButton").setEnabled(true);
						
						MainController.getInstance().getMainGui().recalculateSize();
					}
				}).start();
				
				get("loginButton").setEnabled(false);
				load(true);
				MainController.getInstance().getMainGui().recalculateSize();
			}
			
		});
	}
	
	public void load(boolean toLoad) {
		get("loading").setVisible(toLoad);
	}

	@Override
	public void activate() {
		super.activate();
		get("loading").setVisible(false);
	}
	
	@Override
	public JButton getDefaultButton() {
		return get("loginButton", JButton.class);
	}

	@Override
	public JComponent getDefaultFocus() {
		return get("usernameInput");
	}
	
}
