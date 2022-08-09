package Main;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import view.main_frame.Main_window;

public class MyApp {

	public static void main(String[] args) {

		Main_window mw = Main_window.getInstance();
		mw.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				mw.SaveDataBaseState();
			}
		});
	}
}
