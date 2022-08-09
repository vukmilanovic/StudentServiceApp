package view.listen_test;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;

public class Dialog extends JDialog{
	
	private static final long serialVersionUID = -5611623618038751921L;

	public Dialog() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
		int scr_w = screen.width;
		int scr_h = screen.height;
		
		setSize(scr_w / 4, scr_h / 4);
		setTitle("Dijalog");
		setLocationRelativeTo(null);
		
		JButton btn1 = new JButton("Dugme1");
		JButton btn2 = new JButton("Dugme2");
		
		
		this.add(btn1);
		this.add(btn2);
		
		setVisible(true);
		
	}
	
}
