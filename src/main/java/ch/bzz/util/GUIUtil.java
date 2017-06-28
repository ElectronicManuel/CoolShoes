package ch.bzz.util;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GUIUtil {

	public static JPanel wrap(JComponent toWrap) {
		JPanel wrapper = new JPanel();
		wrapper.add(toWrap);
		
		return wrapper;
	}
	
	public static JScrollPane scrollify(JComponent toScrollify, Dimension size) {
		JScrollPane pane = new JScrollPane(toScrollify, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		pane.setPreferredSize(size);
		
		return pane;
	}
	
}
