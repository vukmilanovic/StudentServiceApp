package view.edit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.CourseClassController;
import controller.GradeController;
import data_base.CourseClassDB;
import data_base.GradeDB;
import data_base.StudentDB;
import model_sistema.Adress;
import model_sistema.CourseClass;
import model_sistema.Date;
import model_sistema.Grade;
import model_sistema.IndexNum;
import model_sistema.Status;
import model_sistema.Student;
import utility.Parser;
import view.add.Add_Grade;
import view.add.Add_Subject_To_Student;
import view.main_frame.Main_Window_Tabs;
import view.main_frame.Main_window;
import view.tables.AbstractPassedSubjectsTableModel;
import view.tables.AbstractUnpassedSubjectsTabelModel;
import view.tables.PassedSubjects;
import view.tables.UnpassedSubjects;

public class Edit_Student_Tabs extends JTabbedPane {
	
	private static final long serialVersionUID = -8422314943789432028L;

	private Student selectedStudent;
	
	private JButton ConfirmButton = new JButton(getWord("buttonsConfirm"));
	private JButton CancelButton  = new JButton(getWord("buttonsCancel"));
	
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
	
	private static PassedSubjects tableOfPassedSubjects;
	private static UnpassedSubjects tableOfUnpassedSubjects;
	
	public Edit_Student_Tabs(Student selectedStudent) {
		
		super();
		
		this.selectedStudent = StudentDB.getInstance().getStudentByIdx(selectedStudent.getIdx());
		
		//###################################################
		JPanel panInfo = new JPanel();
		BoxLayout boxInfo = new BoxLayout(panInfo, BoxLayout.Y_AXIS);
		panInfo.setLayout(boxInfo);
		
		//dimenzije labela i textfield-ova
		Dimension dimension = new Dimension(200, 30);
				
		//dimenzije dugmadi
		Dimension dim_button = new Dimension(100, 30);
				
		//Ime
		JPanel panIme = new JPanel();
		JLabel labIme = new JLabel(getWord("nameAddStudent"));
		labIme.setPreferredSize(dimension);
				
		JTextField txtIme = new JTextField(this.selectedStudent.getName());
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
		JLabel labPrezime = new JLabel(getWord("surnameAddStudent"));
		labPrezime.setPreferredSize(dimension);
		
		JTextField txtPrezime = new JTextField(this.selectedStudent.getSurname());
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
		JLabel labBDate = new JLabel(getWord("bdateAddStudent"));
		labBDate.setPreferredSize(dimension);
			
		JTextField txtBDate = new JTextField(this.selectedStudent.getDob().toString());
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
		JLabel labAdress = new JLabel(getWord("adressAddStudent"));
		labAdress.setPreferredSize(dimension);
				
		JTextField txtAdress = new JTextField(this.selectedStudent.getAdr().toString());
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
		JLabel labNumber = new JLabel(getWord("numAddStudent"));
		labNumber.setPreferredSize(dimension);
				
		JTextField txtNumber = new JTextField(this.selectedStudent.getTel());
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
		JLabel labMail = new JLabel(getWord("mailAddStudent"));
		labMail.setPreferredSize(dimension);
				
		JTextField txtMail = new JTextField(this.selectedStudent.getMail());
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
		JLabel labIndxNum = new JLabel(getWord("indxNumAddStudent"));
		labIndxNum.setPreferredSize(dimension);
				
		JTextField txtIndxNum = new JTextField(this.selectedStudent.getIdx().toString());
		txtIndxNum.setPreferredSize(dimension);
		txtIndxNum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentIndxNum = txtIndxNum.getText();
				studentIndex = Parser.parseIndexFromString(studentIndxNum);
				for(Student std : StudentDB.getInstance().getStudent_list()) {
					if(std.getIdx().equals(studentIndex) && !selectedStudent.getIdx().equals(studentIndex)) {
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
		JLabel labYoE = new JLabel(getWord("yoeAddStudent"));
		labYoE.setPreferredSize(dimension);
				
		JTextField txtYoE = new JTextField(String.valueOf(this.selectedStudent.getEnrollyear()));
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
		JLabel labCurrentY = new JLabel(getWord("currentYAddStudent"));
		labCurrentY.setPreferredSize(dimension);
				
		String[] years = { getWord("currentYearFirst"), getWord("currentYearSecond"), getWord("currentYearThird"), getWord("currentYearFourth") };
				
		JComboBox<String> combo1 = new JComboBox<String>(years);
		combo1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String studentCurrentY = years[combo1.getSelectedIndex()];
				studentCurrent = combo1.getSelectedIndex() + 1;
				enableConfirm();
			}
		});
				
		panCurrentY.add(labCurrentY);
		panCurrentY.add(combo1);
				
		combo1.setPreferredSize(dimension);
		combo1.setSelectedIndex(this.selectedStudent.getCurryear() - 1);
				
		//Naèin finansiranja
		JPanel panFinancials = new JPanel();
		JLabel labFinancials = new JLabel(getWord("financialsAddStudent"));
		labFinancials.setPreferredSize(dimension);
				
		String[] FinancingWays = { getWord("financialsWay2"), getWord("financialsWay1") };
				
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
		combo2.setSelectedIndex(this.selectedStudent.getS().ordinal());
				
		//Dugmad
		JPanel panButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 20));
		ConfirmButton.setPreferredSize(dim_button);
		ConfirmButton.setEnabled(false);
		
		CancelButton.setPreferredSize(dim_button);

		panButtons.add(ConfirmButton);
		panButtons.add(CancelButton);
				
		//Dodavanje
		panInfo.add(Box.createVerticalStrut(25));
		panInfo.add(panIme);
		panInfo.add(Box.createVerticalStrut(2));
		panInfo.add(panPrezime);
		panInfo.add(Box.createVerticalStrut(2));
		panInfo.add(panBDate);
		panInfo.add(Box.createVerticalStrut(2));
		panInfo.add(panAdress);
		panInfo.add(Box.createVerticalStrut(2));
		panInfo.add(panNumber);
		panInfo.add(Box.createVerticalStrut(2));
		panInfo.add(panMail);
		panInfo.add(Box.createVerticalStrut(2));
		panInfo.add(panIndxNum);
		panInfo.add(Box.createVerticalStrut(2));
		panInfo.add(panYoE);
		panInfo.add(Box.createVerticalStrut(2));
		panInfo.add(panCurrentY);
		panInfo.add(Box.createVerticalStrut(2));
		panInfo.add(panFinancials);
		panInfo.add(Box.createVerticalStrut(20));
		panInfo.add(panButtons);
		
		//################################################
		
		//dimenzije dugmeta iz taba polozeni
		Dimension passedButtons_dim = new Dimension(120, 25);
		
		//dimenzije dugmadi iz taba nepolozeni
		Dimension failedButton1_dim = new Dimension(70, 25);
		Dimension failedButton2_dim = new Dimension(80, 25);
		Dimension failedButton3_dim = new Dimension(90, 25);
		
		//dimenzije tabela
		Dimension tables_dim = new Dimension(520, 280);
		
		//################################################
		
		JPanel panPassed = new JPanel();
		BoxLayout boxPassed = new BoxLayout(panPassed, BoxLayout.Y_AXIS);
		panPassed.setLayout(boxPassed);
		
		//dugme
		JPanel panButton1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 10));
		
		this.selectedStudent.setAvg();
		this.selectedStudent.setSumESPB();
		JLabel labGrade = new JLabel(getWord("avgGradeEditStudent") + selectedStudent.getAvg());
		JLabel labESPB = new JLabel(getWord("totalESPBEditStudent") + selectedStudent.getSumESPB());
		
		JButton buttonPassed = new JButton(getWord("buttonRepeal"));
		buttonPassed.setPreferredSize(passedButtons_dim);
		buttonPassed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tableOfPassedSubjects.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, getWord("jopGradeMessage"), getWord("jopUpozorenje"), JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (JOptionPane.showConfirmDialog(null, getWord("jopGradeMessage2"), getWord("jopGradeTitleMessage"), JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION) {
					
					short code = (short) tableOfPassedSubjects.getValueAt(tableOfPassedSubjects.getSelectedRow(), 0);
					Date date = (Date) tableOfPassedSubjects.getValueAt(tableOfPassedSubjects.getSelectedRow(), 4);
					int idx = 0;
					
					ArrayList<Grade> grades = (ArrayList<Grade>) GradeDB.getInstance().getGrades();
					for(Grade g : grades) {
						if(g.getCourse().getCode() == code && g.getD().equals(date)) {
							break;
						}
						idx++;
					}
					
					GradeController.getInstance().RemoveGradeFromStudent(idx, Edit_Student_Tabs.this.selectedStudent.getIdx());
					
					Edit_Student_Tabs.this.selectedStudent.setAvg();
					Edit_Student_Tabs.this.selectedStudent.setSumESPB();
					labGrade.setText(getWord("avgGradeEditStudent") + selectedStudent.getAvg());
					labESPB.setText(getWord("totalESPBEditStudent") + selectedStudent.getSumESPB());
					
					Main_Window_Tabs.getInstance().updateStudentView(null, -1);
				};
			}
		});
		
		panButton1.add(buttonPassed);
		
		//tabela (treba izmeniti u tabelu)
		JPanel panTable1 = new JPanel();
	
		JPanel panPassedSubjects = createPassedSubjectTablePanel();
		panPassedSubjects.setPreferredSize(tables_dim);
		
		panTable1.add(panPassedSubjects, BorderLayout.CENTER);
		
		//prosek i espb bodovi
		
		JPanel panGrade = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//ovde treba kao parametar da se doda i funkcija koja racuna prosecnu ocenu studenda
		
		panGrade.add(labGrade);
		panGrade.add(Box.createHorizontalStrut(35));
		
		JPanel panESPB = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//ovde treba kao parametar da se doda i fja koja racuna ESPB bodove
		
		panESPB.add(labESPB);
		panESPB.add(Box.createHorizontalStrut(35));
		
		//dodavanje svega u panPassed
		
		panPassed.add(Box.createVerticalStrut(15));
		panPassed.add(panButton1);
		panPassed.add(panTable1);
		panPassed.add(panGrade);
		panPassed.add(panESPB);

		//################################################
		
		JPanel panFailed = new JPanel();
		BoxLayout boxFailed = new BoxLayout(panFailed, BoxLayout.Y_AXIS);
		panFailed.setLayout(boxFailed);
		
		//dugmad
		JPanel panButton2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
		
		JButton buttonFailed1 = new JButton(getWord("buttonFailed1"));
		buttonFailed1.setPreferredSize(failedButton1_dim);
		buttonFailed1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Add_Subject_To_Student add_subject_to_student = new Add_Subject_To_Student(selectedStudent);			
			}
		});
		
		JButton buttonFailed2 = new JButton(getWord("buttonFailed2"));
		buttonFailed2.setPreferredSize(failedButton2_dim);
		buttonFailed2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tableOfUnpassedSubjects.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, getWord("jopSubjectMessage"), getWord("jopUpozorenje"), JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(JOptionPane.showConfirmDialog(null, getWord("jopSubjectRemove"), getWord("jopSubjectRemoveTitle"), JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION) {
					
					short code = (short) tableOfUnpassedSubjects.getValueAt(tableOfUnpassedSubjects.getSelectedRow(), 0);
					int idx = 0;
					
					ArrayList<CourseClass> classes = (ArrayList<CourseClass>) CourseClassDB.getInstance().getClasses();
					for(CourseClass cs : classes) {
						if(cs.getCode() == code)
							break;
						idx++;
					}
					
					CourseClassController.getInstance().RemoveCourseFromStudent(idx, Edit_Student_Tabs.this.selectedStudent.getIdx());
				}
			}
		});
		
		JButton buttonFailed3 = new JButton(getWord("buttonFailed3"));
		buttonFailed3.setPreferredSize(failedButton3_dim);
		buttonFailed3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tableOfUnpassedSubjects.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, getWord("jopSubjectMessage"), getWord("jopUpozorenje"), JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				short code = (short) tableOfUnpassedSubjects.getValueAt(tableOfUnpassedSubjects.getSelectedRow(), 0);
				String name = (String) tableOfUnpassedSubjects.getValueAt(tableOfUnpassedSubjects.getSelectedRow(), 1);
				int idx = 0;
				
				ArrayList<CourseClass> classes = (ArrayList<CourseClass>) CourseClassDB.getInstance().getClasses();
				for(CourseClass cs : classes) {
					if(cs.getCode() == code)
						break;
					idx++;
				}
				
				Add_Grade addGrade = new Add_Grade(selectedStudent, idx, code, name);
				
				Edit_Student_Tabs.this.selectedStudent.setAvg();
				Edit_Student_Tabs.this.selectedStudent.setSumESPB();
				labGrade.setText(getWord("avgGradeEditStudent") + selectedStudent.getAvg());
				labESPB.setText(getWord("totalESPBEditStudent") + selectedStudent.getSumESPB());
				
				Main_Window_Tabs.getInstance().updateStudentView(null, -1);
			}
		});
		
		panButton2.add(Box.createHorizontalStrut(10));
		panButton2.add(buttonFailed1);
		panButton2.add(buttonFailed2);
		panButton2.add(buttonFailed3);
		
		
		//tabela (treba izmeniti u tabelu)
		JPanel panTable2 = new JPanel();
			
		JPanel panUnpassedSubjects = createUnPassedSubjectTablePanel();
		panUnpassedSubjects.setPreferredSize(tables_dim);
				
		panTable2.add(panUnpassedSubjects, BorderLayout.NORTH);
		
		//prazan prostor
		JPanel panEmpty = new JPanel();
		
		//dodavanje svega u panFailed
		
		panFailed.add(Box.createVerticalStrut(25));
		panFailed.add(panButton2);
		panFailed.add(panTable2);
		panFailed.add(panEmpty);
		
		//################################################
		
		addTab(getWord("editStudentTab0"), panInfo);
		addTab(getWord("editStudentTab1"), panPassed);
		updatePassedSubjectsView(null, -1);
		addTab(getWord("editStudentTab2"), panFailed);
		updateUnpassedSubjectsView(null, -1);
		
		setVisible(true);
		
	}
	
	public static void updatePassedSubjectsView(String action, int value) {
		AbstractPassedSubjectsTableModel model1 = (AbstractPassedSubjectsTableModel) tableOfPassedSubjects.getModel();
		model1.fireTableDataChanged();
//		validate();
	}
	
	public static void updateUnpassedSubjectsView(String action, int value) {
		AbstractUnpassedSubjectsTabelModel model2 = (AbstractUnpassedSubjectsTabelModel) tableOfUnpassedSubjects.getModel();
		model2.fireTableDataChanged();
//		validate();
	}
	
	public JPanel createPassedSubjectTablePanel() {
		tableOfPassedSubjects = new PassedSubjects();
		
		JScrollPane scrollPane1 = new JScrollPane(tableOfPassedSubjects);
		JPanel passedSubjectsPanel = new JPanel();
		passedSubjectsPanel.setLayout(new BorderLayout());
		passedSubjectsPanel.add(scrollPane1, BorderLayout.CENTER);
		
		return passedSubjectsPanel;
	}
	
	public JPanel createUnPassedSubjectTablePanel() {
		tableOfUnpassedSubjects = new UnpassedSubjects();
		
		JScrollPane scrollPane2 = new JScrollPane(tableOfUnpassedSubjects);
		JPanel unpassedSubjectsPanel = new JPanel();
		unpassedSubjectsPanel.setLayout(new BorderLayout());
		unpassedSubjectsPanel.add(scrollPane2, BorderLayout.CENTER);
		
		return unpassedSubjectsPanel;
	}
	
	public void initComponents() {
		
		//tableOfPassedSubjects.initComponents();
		//tableOfUnpassedSubjects.initComponents();
		
	}
	
	private void enableConfirm() {
		if(studentIme == null || studentPrezime == null || studentDate == null || studentAdr == null || studentTel == null || studentMail == null || studentIndex == null || studentEnroll == -1 || studentCurrent == -1 || studentStatus == null)
			ConfirmButton.setEnabled(false);
		else ConfirmButton.setEnabled(true);
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
	
	public JButton getConfirmButton() {
		return ConfirmButton;
	}
	
	public JButton getCancelButton() {
		return CancelButton;
	}

	public String getStudentIme() {
		return studentIme;
	}

	public String getStudentPrezime() {
		return studentPrezime;
	}

	public Date getStudentDate() {
		return studentDate;
	}

	public Adress getStudentAdr() {
		return studentAdr;
	}

	public String getStudentTel() {
		return studentTel;
	}

	public String getStudentMail() {
		return studentMail;
	}

	public IndexNum getStudentIndex() {
		return studentIndex;
	}

	public int getStudentEnroll() {
		return studentEnroll;
	}

	public int getStudentCurrent() {
		return studentCurrent;
	}

	public Status getStudentStatus() {
		return studentStatus;
	}
	
}
