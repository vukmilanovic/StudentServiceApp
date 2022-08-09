package view.main_frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;


public class StatusBar {
	
	private JLabel statusLabel1;
	private JLabel statusLabel2;
	
	public StatusBar(Main_window mw) {
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setBackground(Color.LIGHT_GRAY);
		
		mw.add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(mw.getWidth(), 20));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		
		statusLabel1 = new JLabel(getWord("statusBarInitialAppName"));
		statusPanel.add(statusLabel1);
		
		statusPanel.add(Box.createGlue());
		
		statusLabel2 = new JLabel(DateFormat.getDateTimeInstance().format(new Date()));
		
		//uzeto sa stackoverflow-a
		new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				statusLabel2.setText(DateFormat.getDateTimeInstance().format(new Date()));	
			}
		}).start();
		
		statusPanel.add(statusLabel2);
		statusPanel.add(Box.createHorizontalStrut(5));
		
	}
	
	public void initComponents() {
		
		statusLabel1.setText(getWord("statusBarInitialAppName"));
		changeTitle();
		statusLabel2.setText(DateFormat.getDateTimeInstance().format(new Date()));
		
	}
	
	public void changeTitle() {
		Main_Window_Tabs mwt = Main_Window_Tabs.getInstance();
		String str = "";
		
		if(mwt.getTitleAt(mwt.getSelectedIndex()).equals("Profesori") || mwt.getTitleAt(mwt.getSelectedIndex()).equals("Professors")) {
			str = "tabProfesori"; 
		} else if (mwt.getTitleAt(mwt.getSelectedIndex()).equals("Studenti") || mwt.getTitleAt(mwt.getSelectedIndex()).equals("Students")) {
			str = "tabStudenti";
		} else if(mwt.getTitleAt(mwt.getSelectedIndex()).equals("Predmeti") || mwt.getTitleAt(mwt.getSelectedIndex()).equals("Subjects")) {
			str = "tabPredmeti";
		} else if(mwt.getTitleAt(mwt.getSelectedIndex()).equals("Katedre") || mwt.getTitleAt(mwt.getSelectedIndex()).equals("Departments")) {
			str = "tabKatedre";
		}
		
		statusLabel1.setText(getWord("statusBarAppName") + getWord(str));
	
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
	
}
