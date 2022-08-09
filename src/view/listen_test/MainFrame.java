package view.listen_test;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainFrame() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
		int scr_w = screen.width;
		int scr_h = screen.height;
		
		setSize(scr_w / 4, scr_h / 4);
		setTitle("Test");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		this.add(panel);
		
		JButton btn = new JButton("Pritisni me");
		btn.addActionListener(new Listener());
		panel.add(btn);
	}
	
}
