package view.main_frame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import view.tables.AbstractCourseChairTableModel;
import view.tables.AbstractProfessorTableModel;
import view.tables.AbstractStudentTableModel;
import view.tables.AbstractSubjectTableModel;
import view.tables.CourseChairTable;
import view.tables.ProfessorTable;
import view.tables.StudentTable;
import view.tables.SubjectTable;

public class Main_Window_Tabs extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1754096990014180101L;
	
	private StudentTable tableOfStudents;
	private ProfessorTable tableOfProfessors;
	private SubjectTable tableOfSubjects;
	private CourseChairTable tableOfDepartments;
	private static Main_Window_Tabs instance = null;
	
	public static Main_Window_Tabs getInstance() {
		if(instance == null) {
			instance = new Main_Window_Tabs();
		}
		return instance;
	}
	
	private Main_Window_Tabs() {
			
			addTab(getWord("tabStudenti"), createStudentTablePanel());
			updateStudentView(null, -1);
			addTab(getWord("tabProfesori"), createProfessorTablePanel());
			updateProfessorView(null, -1);
			addTab(getWord("tabPredmeti"), createSubjectTablePanel());
			updateSubjectView(null, -1);
			addTab(getWord("tabKatedre"), createDepartmentTablePanel());
			updateDepartmentView(null, -1);
			
	}
	
	public void initComponenets() {
		
		setTitleAt(0, getWord("tabStudenti"));
		setTitleAt(1, getWord("tabProfesori"));
		setTitleAt(2, getWord("tabPredmeti"));
		setTitleAt(3, getWord("tabKatedre"));
		
		tableOfStudents.initComponents();
		tableOfProfessors.initComponents();
		tableOfSubjects.initComponents();
		tableOfDepartments.initComponents();

	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CourseChairTable getTableOfDepartments() {
		return tableOfDepartments;
	}

	public void updateStudentView(String action, int value) {
		AbstractStudentTableModel model1 = (AbstractStudentTableModel) tableOfStudents.getModel();
		model1.fireTableDataChanged();
		validate();
	}
	
	public void updateProfessorView(String action, int value) {
		AbstractProfessorTableModel model2 = (AbstractProfessorTableModel) tableOfProfessors.getModel();
		model2.fireTableDataChanged();
		validate();
	}
	
	public void updateSubjectView(String action, int value) {
		AbstractSubjectTableModel model3 = (AbstractSubjectTableModel) tableOfSubjects.getModel();
		model3.fireTableDataChanged();
		validate();
	}
	
	public void updateDepartmentView(String action, int value) {
		AbstractCourseChairTableModel model4 = (AbstractCourseChairTableModel) tableOfDepartments.getModel();
		model4.fireTableDataChanged();
		validate();
	}
	
	public JPanel createStudentTablePanel() {
		tableOfStudents = new StudentTable();
		
		JScrollPane scrollPane1 = new JScrollPane(tableOfStudents);
		JPanel studentTabPanel = new JPanel();
		studentTabPanel.setLayout(new BorderLayout());
		studentTabPanel.add(scrollPane1, BorderLayout.CENTER);
		
		return studentTabPanel;
	}
	
	public JPanel createProfessorTablePanel() {
		tableOfProfessors = new ProfessorTable();
		
		JScrollPane scrollPane2 = new JScrollPane(tableOfProfessors);
		JPanel professorTabPanel = new JPanel();
		professorTabPanel.setLayout(new BorderLayout());
		professorTabPanel.add(scrollPane2, BorderLayout.CENTER);
		
		return professorTabPanel;
	}
	
	public JPanel createSubjectTablePanel() {
		tableOfSubjects = new SubjectTable();
		
		JScrollPane scrollPane3 = new JScrollPane(tableOfSubjects);
		JPanel subjectTabPanel = new JPanel();
		subjectTabPanel.setLayout(new BorderLayout());
		subjectTabPanel.add(scrollPane3, BorderLayout.CENTER);
		
		return subjectTabPanel;
	}
	
	public JPanel createDepartmentTablePanel() {
		tableOfDepartments = new CourseChairTable();
		
		JScrollPane scrollPane4 = new JScrollPane(tableOfDepartments);
		JPanel departmentTabPanel = new JPanel();
		departmentTabPanel.setLayout(new BorderLayout());
		departmentTabPanel.add(scrollPane4, BorderLayout.CENTER);
		
		return departmentTabPanel;
	}
	
	public JTable getTableOfStudents() {
		return tableOfStudents;
	}
	
	public JTable getTableOfProfessors() {
		return tableOfProfessors;
	}
	
	public JTable getTableOfSubjects() {
		return tableOfSubjects;
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
	
}
