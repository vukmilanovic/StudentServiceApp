package view.add;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.security.auth.Subject;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CourseClassController;
import data_base.CourseClassDB;
import model_sistema.CourseClass;
import model_sistema.Semester;
import utility.Parser;
import view.main_frame.Main_Window_Tabs;
import view.main_frame.Main_window;

public class Add_Subject extends JDialog {

	private String subjectID = null;
	private String subjectName = null;
	private String ESPBSubject = null;
	private int yearSubject = -1;
	private Semester semesterSubject = null;
	
	private JLabel labID;
	private JLabel labName;
	private JLabel labYear;
	private JLabel labSemester;
	private JLabel labESPB;
	
	private JButton ConfirmButton = new JButton(getWord("buttonsConfirm"));
	private JButton CancelButton = new JButton(getWord("buttonsCancel"));
	
	public Add_Subject() {
		
		super();
		
		setTitle(getWord("titleAddSubject"));
		setSize(450,400);
		setLocationRelativeTo(Main_Window_Tabs.getInstance());
		setModal(true);
		
		JPanel panMain = new JPanel();
		BoxLayout boxMain = new BoxLayout(panMain, BoxLayout.Y_AXIS);
		panMain.setLayout(boxMain);
		
		//dimenzije labela
		Dimension lab_dim = new Dimension(100 ,25);
		
		//dimenzije dugmadi
		Dimension button_dim = new Dimension(90 ,25);
				
		//dimenzije textfield-ova
		Dimension txtf_dim = new Dimension(200 ,25);
		
		//Sifra
		JPanel panID = new JPanel();
		labID = new JLabel(getWord("codeAddSubject"));
		labID.setPreferredSize(lab_dim);
	
		JTextField txtID = new JTextField();
		txtID.setPreferredSize(txtf_dim);
		txtID.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				subjectID = txtID.getText();
				if(subjectID.matches("[a-zA-Z]+")) {
					subjectID = null;
				}
				for(CourseClass subj : CourseClassDB.getInstance().getClasses()) {
					if(subj.getCode() == Short.parseShort(subjectID)) {
						subjectID = null;
						break;
					}
				}
				enableConfirm();
			}
		});
		
		panID.add(labID);
		panID.add(txtID);
		
		//Naziv
		JPanel panName = new JPanel();
		labName = new JLabel(getWord("nameOfSAddSubject"));
		labName.setPreferredSize(lab_dim);
	
		JTextField txtName = new JTextField();
		txtName.setPreferredSize(txtf_dim);
		txtName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				subjectName = txtName.getText();
				enableConfirm();
			}
		});
		
		panName.add(labName);
		panName.add(txtName);
		
		//Godina (jcombobox)
		JPanel panYear = new JPanel();
		labYear = new JLabel(getWord("yearOfStudyAddSubject"));
		labYear.setPreferredSize(lab_dim);
		
		String[] years = { "1.", "2.", "3.", "4." };
		
		JComboBox<String> combo1 = new JComboBox<String>(years);
		combo1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//	String subjectYear = years[combo1.getSelectedIndex()];
				yearSubject = combo1.getSelectedIndex() + 1;
				enableConfirm();
			}
		});
		
		panYear.add(labYear);
		panYear.add(combo1);
		
		combo1.setPreferredSize(txtf_dim);
		combo1.setSelectedIndex(0);
			
		//Semestar (jcombo)
		JPanel panSemester = new JPanel();
		labSemester = new JLabel(getWord("semesterAddSubject"));
		labSemester.setPreferredSize(lab_dim);
		
		String[] semesters = {getWord("winterAddSubject"), getWord("summerAddSubject") };
		
		JComboBox<String> combo2 = new JComboBox<String>(semesters);
		combo2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String subjectSemester = semesters[combo2.getSelectedIndex()];
				semesterSubject = Parser.parseSemesterFromString(subjectSemester);
				enableConfirm();
			}
		});
		
		panSemester.add(labSemester);
		panSemester.add(combo2);
		
		combo2.setPreferredSize(txtf_dim);
		combo2.setSelectedIndex(0);
		
		//ESPB
		JPanel panESPB = new JPanel();
		labESPB = new JLabel(getWord("ESPBPointsAddSubject"));
		labESPB.setPreferredSize(lab_dim);
	
		JTextField txtESPB = new JTextField();
		txtESPB.setPreferredSize(txtf_dim);
		txtESPB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ESPBSubject = txtESPB.getText();
				if(ESPBSubject.matches("[a-zA-Z]+")) {
					ESPBSubject = null;
				}
				enableConfirm();
			}
		});
		
		panESPB.add(labESPB);
		panESPB.add(txtESPB);
		
		//Dugmad
		JPanel panButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 20));
		ConfirmButton.setEnabled(false);
		ConfirmButton.setPreferredSize(button_dim);
		ConfirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					CourseClassController.getInstance().AddCourseClass(Short.parseShort(subjectID), subjectName, yearSubject, semesterSubject, Integer.parseInt(ESPBSubject));
					dispose();
			}
		});
		
		CancelButton.setPreferredSize(button_dim);
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
		panMain.add(panID);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panName);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panYear);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panSemester);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panESPB);
		//panMain.add(Box.createVerticalStrut(2));
		//panMain.add(panProfessor);
		panMain.add(Box.createVerticalStrut(20));
		panMain.add(panButtons);
		
		add(panMain, BorderLayout.CENTER);
		setVisibility(false);
		
	}
	
	private void enableConfirm() {
		if(subjectID == null || subjectName == null || ESPBSubject == null || yearSubject == -1 || semesterSubject == null) 
			ConfirmButton.setEnabled(false);
		
		else ConfirmButton.setEnabled(true);
	}
	
	public void setVisibility(boolean bool) {
		setVisible(bool);
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
}
