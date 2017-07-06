package ch.bzz.util;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Diese Klasse enthält kleine Methoden um die GUI Erstellung zu vereinfachen
 * @author Emanuel
 * @version 0.0.1-SNAPSHOT
 * Datum: 04.07.2017
 */
public class GUIUtil {

	/**
	 * Verpackt einen Komponenten in einer JPanel, besonders nützlich wenn man einen JButton in ein Borderlayout packen will ohne dass dieser den gesamten Platz verbraucht
	 * 
	 * @param toWrap
	 * @return JPanel mit dem Komponenten darin enthalten
	 */
	public static JPanel wrap(JComponent toWrap) {
		JPanel wrapper = new JPanel();
		wrapper.add(toWrap);
		
		return wrapper;
	}

	/**
	 * Verpackt einen Komponenten in einer JScrollPanel
	 * @param toScrollify
	 * @param size
	 * @return JScrollPanel mit der entsprechenden Grösse und dem Komponenten darin
	 */
	public static JScrollPane scrollify(JComponent toScrollify, Dimension size) {
		JScrollPane pane = new JScrollPane(toScrollify, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		pane.setPreferredSize(size);
		
		return pane;
	}
	
}
