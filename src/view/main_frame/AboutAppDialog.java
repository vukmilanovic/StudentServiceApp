package view.main_frame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutAppDialog extends JDialog {
	
	public AboutAppDialog() {
		
		super();
		
		setTitle(getWord("aboutAppDialogTitle"));
		setSize(600, 630);
		setLocationRelativeTo(null);
		setModal(true);
		
		JPanel panMain = new JPanel();
		BoxLayout boxMain=new BoxLayout(panMain, BoxLayout.Y_AXIS);
		panMain.setLayout(boxMain);

		JPanel panText = new JPanel();
		
		String text = getWord("about");
		
		JLabel labText = new JLabel(text);
		labText.setPreferredSize(new Dimension(500, 550));
		
		panText.add(labText);
		
		panMain.add(panText);
		add(panMain, BorderLayout.CENTER);
		setVisible(true);
		
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
	
}
