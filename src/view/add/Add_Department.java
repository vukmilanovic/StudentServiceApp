package view.add;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model_sistema.Professor;
import view.main_frame.Main_window;

public class Add_Department extends JDialog {

	private JButton ConfirmButton = new JButton("Potvrdi");
	private JButton CancelButton = new JButton("Odustani");
	
	private int departmentCode;
	private String departmentName;
	private Professor chairMan;
	
	public Add_Department() {
		
		super();
		
		setTitle("Add department");
		setSize(450, 350);
		setLocationRelativeTo(null);
		//setModal(true);
		
		//main panel
		JPanel panMain = new JPanel();
		BoxLayout boxMain=new BoxLayout(panMain, BoxLayout.Y_AXIS);
		panMain.setLayout(boxMain);
		
		//dimenzije labela i textfield-ova
		Dimension dimension_txt = new Dimension(220, 30);
		Dimension dimension_lab = new Dimension(120,30);	
		
		//dimenzije dugmadi
		Dimension dim_button = new Dimension(90, 30);
		Dimension dim_button2 = new Dimension(42,30);
		
		//Sifra
		JPanel panID = new JPanel();
		JLabel labID = new JLabel("Code*");
		labID.setPreferredSize(dimension_lab);
				
		JTextField txtID = new JTextField();
		txtID.setPreferredSize(dimension_txt);
				
		panID.add(labID);
		panID.add(txtID);
				
		//Naziv
		JPanel panName = new JPanel();
		JLabel labName = new JLabel("Name*");
		labName.setPreferredSize(dimension_lab);
				
		JTextField txtName = new JTextField();
		txtName.setPreferredSize(dimension_txt);
		
		panName.add(labName);
		panName.add(txtName);
		
		//chairman
		JPanel panChairMan = new JPanel();
		
		JButton removeButton = new JButton("-");
		removeButton.setPreferredSize(dim_button2);
		
		JLabel labChairMan = new JLabel("Chairman*");
		labChairMan.setPreferredSize(dimension_lab);
		
		JTextField txtChairMan = new JTextField();
		txtChairMan.setPreferredSize(new Dimension(130, 30));
		
		JButton addButton = new JButton("+");
		addButton.setPreferredSize(dim_button2);
		
		panChairMan.add(labChairMan);
		panChairMan.add(txtChairMan);
		panChairMan.add(addButton);
		panChairMan.add(removeButton);
		
		//dugmad
		JPanel panButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 20));
		
		//ConfirmButton.setEnabled(false);
		ConfirmButton.setPreferredSize(dim_button);
		
		CancelButton.setPreferredSize(dim_button);
		
		panButtons.add(ConfirmButton);
		panButtons.add(CancelButton);
		
		//dodavanje na glavni panel
		
		panMain.add(Box.createVerticalStrut(25));
		panMain.add(panID);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panName);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panChairMan);
		panMain.add(Box.createVerticalStrut(5));
		panMain.add(panButtons);
		
		add(panMain, BorderLayout.CENTER);
		setVisibility(true);
	}
	
	public void setVisibility(boolean bool) {
		setVisible(bool);
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
	
}
