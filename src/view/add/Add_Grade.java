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

import controller.CourseClassController;
import controller.GradeController;
import data_base.CourseClassDB;
import model_sistema.CourseClass;
import model_sistema.Date;
import model_sistema.Student;
import utility.Parser;
import view.main_frame.Main_window;

public class Add_Grade extends JDialog {

	private JButton ConfirmButton = new JButton(getWord("buttonsConfirm"));
	
	private short subjectCode;
	private String subjectName;
	private int idxOfSelectedSubject;
	private Student student;
	
	private int grade = -1;
	private Date date = null;
	
	public Add_Grade(Student student, int idx, short code, String name) {
		
		super();
		
		this.student = student;
		this.subjectCode = code;
		this.idxOfSelectedSubject = idx;
		this.subjectName = name;
		
		setTitle(getWord("addGradeTitle"));
		setSize(320, 320);
		setLocationRelativeTo(null);
		setModal(true);
		
		JPanel panMain = new JPanel();
		BoxLayout boxMain=new BoxLayout(panMain, BoxLayout.Y_AXIS);
		panMain.setLayout(boxMain);
		
		//dimenzije labela i textfield-ova
		Dimension dimension = new Dimension(120, 20);
				
		//dimenzije dugmadi
		Dimension dim_button = new Dimension(85, 20);
		
		//Sifra
		JPanel panID = new JPanel();
		JLabel labID = new JLabel(getWord("codeAddSubject"));
		labID.setPreferredSize(dimension);
		
		JTextField txtID = new JTextField(String.valueOf(this.subjectCode));
		txtID.setPreferredSize(dimension);
		txtID.setEditable(false);
		
		panID.add(labID);
		panID.add(txtID);
		
		//Naziv
		JPanel panName = new JPanel();
		JLabel labName = new JLabel(getWord("nameOfSAddSubject"));
		labName.setPreferredSize(dimension);
		
		JTextField txtName = new JTextField(this.subjectName);
		txtName.setPreferredSize(dimension);
		txtName.setEditable(false);
		
		panName.add(labName);
		panName.add(txtName);
		
		
		//Ocena
		JPanel panGrade = new JPanel();
		JLabel labGrade = new JLabel(getWord("gradeAddGrade"));
		labGrade.setPreferredSize(dimension);
		
		String[] grades = { "6", "7", "8", "9", "10" };
		
		JComboBox<String> comboGrades = new JComboBox<String>(grades);
		comboGrades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				grade = comboGrades.getSelectedIndex() + 6;
				enableConfirm();
			}
		});
		
		comboGrades.setPreferredSize(dimension);
		comboGrades.setSelectedIndex(0);
		
		panGrade.add(labGrade);
		panGrade.add(comboGrades);
		
		//Datum
		JPanel panDate = new JPanel();
		JLabel labDate = new JLabel(getWord("dateAddGrade"));
		labDate.setPreferredSize(dimension);
		
		JTextField txtDate = new JTextField();
		txtDate.setPreferredSize(dimension);
		txtDate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String datePS = txtDate.getText();
				date = Parser.parseDateFromString(datePS);
				enableConfirm();
			}
		});
		
		panDate.add(labDate);
		panDate.add(txtDate);
		
		//Dugmad
		JPanel panButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
		ConfirmButton.setEnabled(false);
		ConfirmButton.setPreferredSize(dim_button);
		ConfirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CourseClassController.getInstance().PassCourse(idxOfSelectedSubject, student.getIdx(), grade, date);
				
//				CourseClass course = CourseClassDB.getInstance().getRow(idxOfSelectedSubject);
//				GradeController.getInstance().AddGrade(student, course, code, date);
				dispose();
			}
		});
		
		
		JButton CancelButton = new JButton(getWord("buttonsCancel"));
		CancelButton.setPreferredSize(dim_button);
		CancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		panButtons.add(ConfirmButton);
		panButtons.add(CancelButton);
		
		//Dodavanje u glavni panel
	
		panMain.add(Box.createVerticalStrut(20));
		panMain.add(panID);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panName);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panGrade);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panDate);
		panMain.add(Box.createVerticalStrut(10));
		panMain.add(panButtons);
	
		add(panMain,BorderLayout.CENTER);
        setVisible(true);
	}
	
	private void enableConfirm() {
		if(date == null || grade == -1) {
			ConfirmButton.setEnabled(false);
		} else {
			ConfirmButton.setEnabled(true);
		}
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
	
}
