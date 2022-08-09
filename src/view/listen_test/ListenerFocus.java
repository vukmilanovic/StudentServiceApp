package view.listen_test;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ListenerFocus implements FocusListener{

	public boolean hasfocus;
	
	@Override
	public void focusGained(FocusEvent e) {
		System.out.println("Dobio fokus");
		hasfocus = true;
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		
	}
	
}
