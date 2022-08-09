package view.edit;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;

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
import data_base.GradeDB;
import model_sistema.CourseClass;
import model_sistema.Date;
import model_sistema.Grade;
import model_sistema.Semester;
import utility.Parser;
import view.add.Add_Professor_To_Subject;
import view.main_frame.Main_Window_Tabs;
import view.main_frame.Main_window;

public class Edit_Subject extends JDialog {

	private String subjectID = null;
	private String subjectName = null;
	private String ESPBSubject = null;
	private int yearSubject = -1;
	private Semester semesterSubject = null;
	
	private CourseClass selectedCourseClass;
	private JButton ConfirmButton = new JButton(getWord("buttonsConfirm"));
	private JButton CancelButton = new JButton(getWord("buttonsCancel"));
	
	public static boolean OdustaniFlag = false;
	
	
	public Edit_Subject(CourseClass selectedCourseClass) {
		
		super();
		
		this.selectedCourseClass = selectedCourseClass;
		
		setTitle(getWord("titleEditSubject"));
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
		
		//dimenzije dugmica
		Dimension lButton_dim = new Dimension(42 ,25);
				
		//dimenzije textfield-ova
		Dimension txtf_dim = new Dimension(250 ,25);
		
		//dimenzije profesor textfield-a
		Dimension txtfp_dim = new Dimension(146 ,25);
		
		//Sifra
		JPanel panID = new JPanel();
		JLabel labID = new JLabel(getWord("codeAddSubject"));
		labID.setPreferredSize(lab_dim);
	
		JTextField txtID = new JTextField(String.valueOf(this.selectedCourseClass.getCode()));
		txtID.setPreferredSize(txtf_dim);
		txtID.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				subjectID = txtID.getText();
				if(subjectID.matches("[a-zA-Z]+")) {
					subjectID = null;
				}
				for(CourseClass subj : CourseClassDB.getInstance().getClasses()) {
					if(subj.getCode() == Short.parseShort(subjectID) && !(String.valueOf(subj.getCode()).equals(subjectID))) {
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
		JLabel labName = new JLabel(getWord("nameOfSAddSubject"));
		labName.setPreferredSize(lab_dim);
	
		JTextField txtName = new JTextField(this.selectedCourseClass.getName());
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
		JLabel labYear = new JLabel(getWord("yearOfStudyAddSubject"));
		labYear.setPreferredSize(lab_dim);
		
		String[] years = { "1.", "2.", "3.", "4." };
		
		JComboBox<String> combo1 = new JComboBox<String>(years);
		combo1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String subjectYear = years[combo1.getSelectedIndex()];
				yearSubject = combo1.getSelectedIndex() + 1;
				enableConfirm();
			}
		});
		
		panYear.add(labYear);
		panYear.add(combo1);
		
		combo1.setPreferredSize(txtf_dim);
		combo1.setSelectedIndex(this.selectedCourseClass.getYear() - 1);
			
		//Semestar (jcombo)
		JPanel panSemester = new JPanel();
		JLabel labSemester = new JLabel(getWord("semesterAddSubject"));
		labSemester.setPreferredSize(lab_dim);
		
		String[] semesters = { getWord("winterAddSubject"), getWord("summerAddSubject") };
		
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
		combo2.setSelectedIndex(this.selectedCourseClass.getSem().ordinal());
		
		//ESPB
		JPanel panESPB = new JPanel();
		JLabel labESPB = new JLabel(getWord("ESPBPointsAddSubject"));
		labESPB.setPreferredSize(lab_dim);
	
		JTextField txtESPB = new JTextField(String.valueOf(this.selectedCourseClass.getEspb()));
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
		
		//Profesor
		JPanel panProfessor = new JPanel();
		
		JButton removeButton = new JButton("-");
		if(this.selectedCourseClass.getProf() == null)
			removeButton.setEnabled(false);
		
		JLabel labProfessor = new JLabel(getWord("professorEditSubject"));
		labProfessor.setPreferredSize(lab_dim);
	
		JTextField txtProfessor = new JTextField();
		txtProfessor.setPreferredSize(txtfp_dim);
		if(this.selectedCourseClass.getProf() != null)
			txtProfessor.setText(this.selectedCourseClass.getProf().getSurname() + " " + this.selectedCourseClass.getProf().getName());
		txtProfessor.setEditable(false);
		
		JButton addButton = new JButton("+");
		addButton.setPreferredSize(lButton_dim);
		if(this.selectedCourseClass.getProf() != null)
			addButton.setEnabled(false);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Add_Professor_To_Subject add_professor_to_subject = new Add_Professor_To_Subject(Edit_Subject.this.selectedCourseClass);
				if(!OdustaniFlag) {
					txtProfessor.setText(Edit_Subject.this.selectedCourseClass.getProf().getSurname() + " " + Edit_Subject.this.selectedCourseClass.getProf().getName());
					addButton.setEnabled(false);
					removeButton.setEnabled(true);
				}
			}
		});
		
		
		removeButton.setPreferredSize(lButton_dim);
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (JOptionPane.showConfirmDialog(null, getWord("jopTextEditSubject"), getWord("jopTitleEditSubject"), JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION) {
					int idx = 0;
					ArrayList<CourseClass> classes = (ArrayList<CourseClass>) CourseClassDB.getInstance().getClasses();
					for(CourseClass cs : classes) {
						if(cs.getCode() == Edit_Subject.this.selectedCourseClass.getCode())
							break;
						idx++;
					}	
			
				CourseClassController.getInstance().RemoveCourseFromProfessor(idx, Edit_Subject.this.selectedCourseClass.getProf().getIDnum());
				txtProfessor.setText("");
				addButton.setEnabled(true);
				removeButton.setEnabled(false);
				
				}
			}
		});
		
		panProfessor.add(labProfessor);
		panProfessor.add(txtProfessor);
		panProfessor.add(Box.createHorizontalStrut(1));
		panProfessor.add(addButton);
		panProfessor.add(Box.createHorizontalStrut(1));
		panProfessor.add(removeButton);
		
		
		//Dugmad
		JPanel panButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 20));
		ConfirmButton.setEnabled(false);
		ConfirmButton.setPreferredSize(button_dim);
		ConfirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = 0;
				for(CourseClass cs : CourseClassDB.getInstance().getClasses()) {
					if(cs.getCode() == Edit_Subject.this.selectedCourseClass.getCode())
						break;
					idx++;
				}
				
				CourseClassController.getInstance().EditCourseClass(idx, Short.parseShort(subjectID), subjectName, yearSubject, semesterSubject, Integer.parseInt(ESPBSubject));
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
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panProfessor);
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