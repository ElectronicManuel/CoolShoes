package ch.bzz.util;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class GUIUtil {

	public static JPanel wrap(JComponent toWrap) {
		JPanel wrapper = new JPanel();
		wrapper.add(toWrap);
		
		return wrapper;
	}
	
}
