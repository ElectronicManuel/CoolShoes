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

/**
 * Dies ist der Logintab in dem sich der Benutzer anmelden kann
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */
public class LoginTab extends CoolTab implements ActionListener {
	
	/**
	 * Initialisiert dem ActionListener für den Loginbutton
	 */
	private void initListeners() {
		get("loginButton", JButton.class).addActionListener(this);
	}
	
	/**
	 * Actionlistener des Login Knopfes
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		final String email = get("emailInput", JTextField.class).getText();
		final String pw = String.valueOf(get("passwordInput", JPasswordField.class).getPassword());

		new Thread(new Runnable() {

			public void run() {
				MainController.getInstance().getLoginCtrl().login(email, pw);
				load(false);
				get("loginButton").setEnabled(true);

				MainController.getInstance().getMainGui().recalculateSize();
			}
		}).start();

		get("loginButton").setEnabled(false);
		load(true);
		MainController.getInstance().getMainGui().recalculateSize();
	}
	
	/**
	 * Zeigt / versteckt den Preloader
	 */
	public void load(boolean toLoad) {
		get("loading").setVisible(toLoad);
		MainController.getInstance().getMainGui().recalculateSize();
	}

	/**
	 * Siehe Cooltab
	 */
	@Override
	public JButton getDefaultButton() {
		return get("loginButton", JButton.class);
	}

	/**
	 * Siehe Cooltab
	 */
	@Override
	public JComponent getDefaultFocus() {
		return get("emailInput");
	}

	/**
	 * Siehe Cooltab
	 */
	@Override
	protected void initBaseComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		
		addComp("emailLabel", new JLabel("Email"));
		
		add(get("emailLabel"), c);
		c.gridx = 1;
		
		addComp("emailInput", new JTextField(40));
		add(get("emailInput"), c);
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
		addComp("loading", loading);
		add(loading, c);

		initListeners();
	}

	/**
	 * Siehe Cooltab
	 */
	@Override
	protected void loadDynamicContent() {
		get("loading", JProgressBar.class).setVisible(false);
	}

	/**
	 * Siehe Cooltab
	 */
	@Override
	protected void unloadDynamicContent() {
		get("emailInput", JTextField.class).setText("");
		get("passwordInput", JPasswordField.class).setText("");
	}

}
