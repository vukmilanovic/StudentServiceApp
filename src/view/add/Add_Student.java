package view.add;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.StudentController;
import data_base.StudentDB;
import model_sistema.Adress;
import model_sistema.Date;
import model_sistema.IndexNum;
import model_sistema.Status;
import model_sistema.Student;
import utility.Parser;
import view.main_frame.Main_Window_Tabs;
import view.main_frame.Main_window;

public class Add_Student extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9031316069564573594L;
	
	private String studentIme = null;
	private String studentPrezime = null;
	private Date studentDate = null;
	private Adress studentAdr = null;
	private String studentTel = null;
	private String studentMail = null;
	private IndexNum studentIndex = null;
	private int studentEnroll = -1;
	private int studentCurrent = -1;
	private Status studentStatus = null;
	
	private JLabel labIme;
	private JLabel labPrezime;
	private JLabel labBDate;
	private JLabel labAdress;
	private JLabel labNumber;
	private JLabel labYoE;
	private JLabel labIndxNum;
	private JLabel labMail;
	private JLabel labCurrentY;
	private JLabel labFinancials;
	
	private JButton ConfirmButton = new JButton(getWord("buttonsConfirm"));
	private JButton CancelButton = new JButton(getWord("buttonsCancel"));
	
	public Add_Student() {
		
		super();
		
		setTitle(getWord("titleAddStudent"));
		setSize(600,600);
		setLocationRelativeTo(Main_Window_Tabs.getInstance()); //centrirati u odnosnu san glavni prozor
		setModal(true);
		
		JPanel panMain=new JPanel();
		BoxLayout boxMain=new BoxLayout(panMain, BoxLayout.Y_AXIS);
		panMain.setLayout(boxMain);
		
		//dimenzije labela i textfield-ova
		Dimension dimension = new Dimension(200, 30);
		
		//dimenzije dugmadi
		Dimension dim_button = new Dimension(100, 30);
		
		//Ime
		JPanel panIme = new JPanel();
		labIme = new JLabel(getWord("nameAddStudent"));
		labIme.setPreferredSize(dimension);
		
		JTextField txtIme = new JTextField();
		txtIme.setPreferredSize(dimension);
		txtIme.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				studentIme = txtIme.getText();
				if(studentIme.matches(".*\\d.*")) studentIme = null;
				enableConfirm();
			}
		});
		
		panIme.add(labIme);
		panIme.add(txtIme);
		
		//Prezime
		JPanel panPrezime = new JPanel();
		labPrezime = new JLabel(getWord("surnameAddStudent"));
		labPrezime.setPreferredSize(dimension);
		
		JTextField txtPrezime = new JTextField();
		txtPrezime.setPreferredSize(dimension);
		txtPrezime.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				studentPrezime = txtPrezime.getText();
				if(studentIme.matches(".*\\d.*")) studentIme = null;
				enableConfirm();
			}
		});
		
		panPrezime.add(labPrezime);
		panPrezime.add(txtPrezime);
		
		//Datum roðenja
		JPanel panBDate = new JPanel();
		labBDate = new JLabel(getWord("bdateAddStudent"));
		labBDate.setPreferredSize(dimension);
		
		JTextField txtBDate = new JTextField();
		txtBDate.setPreferredSize(dimension);
		txtBDate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentBDate = txtBDate.getText();
				studentDate = Parser.parseDateFromString(studentBDate);
				enableConfirm();
			}
		});
		
		panBDate.add(labBDate);
		panBDate.add(txtBDate);
		
		//Adresa stanovanja
		JPanel panAdress = new JPanel();
		labAdress = new JLabel(getWord("adressAddStudent"));
		labAdress.setPreferredSize(dimension);
		
		JTextField txtAdress = new JTextField();
		txtAdress.setPreferredSize(dimension);
		txtAdress.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentAdressTemp = txtAdress.getText();
				studentAdr = Parser.parseAdressFromString(studentAdressTemp);
				enableConfirm();
			}
		});
		
		panAdress.add(labAdress);
		panAdress.add(txtAdress);
		
		//Broj telefona
		JPanel panNumber = new JPanel();
		labNumber = new JLabel(getWord("numAddStudent"));
		labNumber.setPreferredSize(dimension);
		
		JTextField txtNumber = new JTextField();
		txtNumber.setPreferredSize(dimension);
		txtNumber.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				studentTel = txtNumber.getText();
				if(studentTel.matches("[a-zA-Z]+")) studentTel = null;
				enableConfirm();
			}
		});
		
		panNumber.add(labNumber);
		panNumber.add(txtNumber);
		
		//E-mail adresa
		JPanel panMail = new JPanel();
		labMail = new JLabel(getWord("mailAddStudent"));
		labMail.setPreferredSize(dimension);
		
		JTextField txtMail = new JTextField();
		txtMail.setPreferredSize(dimension);
		txtMail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				studentMail = txtMail.getText();
				studentMail = Parser.parseEmailFromString(studentMail);
				enableConfirm();
			}
		});
		
		panMail.add(labMail);
		panMail.add(txtMail);
		
		//Broj indeksa
		JPanel panIndxNum = new JPanel();
		labIndxNum = new JLabel(getWord("indxNumAddStudent"));
		labIndxNum.setPreferredSize(dimension);
		
		JTextField txtIndxNum = new JTextField();
		txtIndxNum.setPreferredSize(dimension);
		txtIndxNum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentIndxNum = txtIndxNum.getText();
				studentIndex = Parser.parseIndexFromString(studentIndxNum);
				for(Student std : StudentDB.getInstance().getStudent_list()) {
					if(std.getIdx().equals(studentIndex)) {
						studentIndex = null;
						break;
					}
				}
				enableConfirm();
			}
		});
		
		panIndxNum.add(labIndxNum);
		panIndxNum.add(txtIndxNum);
		
		//Godina upisa
		JPanel panYoE = new JPanel();
		labYoE = new JLabel(getWord("yoeAddStudent"));
		labYoE.setPreferredSize(dimension);
		
		JTextField txtYoE = new JTextField();
		txtYoE.setPreferredSize(dimension);
		txtYoE.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentYoE = txtYoE.getText();
				if(studentYoE.matches("[a-zA-Z]+")) studentYoE = null;
				studentEnroll = Integer.parseInt(studentYoE);
				if(studentEnroll <= 1900 || studentEnroll >= 2100) studentEnroll = -1;
				enableConfirm();
			}
		});
		
		panYoE.add(labYoE);
		panYoE.add(txtYoE);
		
		//Trenutna godina studija
		JPanel panCurrentY = new JPanel();
		labCurrentY = new JLabel(getWord("currentYAddStudent"));
		labCurrentY.setPreferredSize(dimension);
		
		String[] years = { getWord("currentYearFirst"), getWord("currentYearSecond"), getWord("currentYearThird"), getWord("currentYearFourth") };
		JComboBox<String> combo1 = new JComboBox<String>(years);
		combo1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//String studentCurrentY = years[combo1.getSelectedIndex()];
				studentCurrent = combo1.getSelectedIndex() + 1;
				enableConfirm();
			}
		});
		
		panCurrentY.add(labCurrentY);
		panCurrentY.add(combo1);
		
		combo1.setPreferredSize(dimension);
		combo1.setSelectedIndex(0);
		
		//Naèin finansiranja
		JPanel panFinancials = new JPanel();
		labFinancials = new JLabel(getWord("financialsAddStudent"));
		labFinancials.setPreferredSize(dimension);
		
		String[] FinancingWays = { getWord("financialsWay1"), getWord("financialsWay2") };
		
		JComboBox<String> combo2 = new JComboBox<String>(FinancingWays);
		combo2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentFinancials = FinancingWays[combo2.getSelectedIndex()];
				studentStatus = Parser.parseStatusFromString(studentFinancials);
				enableConfirm();
			}
		});
		
		panFinancials.add(labFinancials);
		panFinancials.add(combo2);
		
		combo2.setPreferredSize(dimension);
		combo2.setSelectedIndex(0);
		
		//Dugmad
		JPanel panButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 20));
		ConfirmButton.setEnabled(false);
		ConfirmButton.setPreferredSize(dim_button);
		ConfirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentController.getInstance().AddStudent(studentIme, studentPrezime, studentDate, studentAdr, studentTel, studentMail, studentIndex, studentEnroll, studentCurrent, studentStatus);
				dispose();
				}
			});
		
		CancelButton.setPreferredSize(dim_button);
		CancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		panButtons.add(ConfirmButton);
		panButtons.add(CancelButton);
		
		//Dodavanje
		panMain.add(Box.createVerticalStrut(25));
		panMain.add(panIme);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panPrezime);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panBDate);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panAdress);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panNumber);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panMail);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panIndxNum);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panYoE);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panCurrentY);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panFinancials);
		panMain.add(Box.createVerticalStrut(20));
		panMain.add(panButtons);
		
        add(panMain,BorderLayout.CENTER);
        setVisibility(false);
        
	}
	
//	public void initComponenets() {
//		
//		setTitle(getWord("titleAddStudent"));
//		labIme.setText(getWord("nameAddStudent"));
//		labPrezime.setText(getWord("surnameAddStudent"));
//		labBDate.setText(getWord("bdateAddStudent"));
//		labAdress.setText(getWord("adressAddStudent"));
//		labNumber.setText(getWord("numAddStudent"));
//		labYoE.setText(getWord("yoeAddStudent"));
//		labIndxNum.setText(getWord("indxNumAddStudent"));
//		labMail.setText(getWord("mailAddStudent"));
//		labCurrentY.setText(getWord("currentYAddStudent"));
//		labFinancials.setText(getWord("financialsAddStudent"));
//		
//		ConfirmButton.setText(getWord("buttonsConfirm"));
//		CancelButton.setText(getWord("buttonsCancel"));
//	
//	}
	
	public void enableConfirm() {
		if(studentIme == null || studentPrezime == null || studentDate == null || studentAdr == null || studentTel == null || studentMail == null || studentIndex == null || studentEnroll == -1 || studentCurrent == -1 || studentStatus == null)
			ConfirmButton.setEnabled(false);
		else ConfirmButton.setEnabled(true);
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
	
	public void setVisibility(boolean bool) {
		setVisible(bool);
	}
}
