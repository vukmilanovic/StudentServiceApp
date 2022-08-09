package view.add;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controller.CourseClassController;
import data_base.CourseClassDB;
import model_sistema.CourseClass;
import model_sistema.Grade;
import model_sistema.Student;
import view.edit.Edit_Student_Tabs;
import view.main_frame.Main_window;

public class Add_Subject_To_Student extends JDialog {

	private Student student;
	
	public Add_Subject_To_Student(Student student) {
		
		super();
		
		this.student = student;
		
		setTitle(getWord("addSubjectToStudentTitle"));
		setSize(400,350);
		setLocationRelativeTo(null);
		setModal(true);
		
		JPanel panMain = new JPanel();
		BoxLayout boxMain = new BoxLayout(panMain, BoxLayout.Y_AXIS);
		panMain.setLayout(boxMain);
		
		//dimenzije scroll prozora
		Dimension scroll_dim = new Dimension(300, 200);
		
		//dimenzije dugmadi
		Dimension button_dim = new Dimension(85, 25);
		
		//panel za scroll_prozor
		JPanel panScroll = new JPanel();
		
		ArrayList<CourseClass> classesDB = (ArrayList<CourseClass>) CourseClassDB.getInstance().getClasses();
		ArrayList<CourseClass> classes = new ArrayList<CourseClass>(classesDB);
		
		for(CourseClass studentUnpassedCourses : this.student.getFailed()) {
			for(CourseClass cs : classes) {
				if(studentUnpassedCourses.getCode() == cs.getCode()) {
					classes.remove(cs);
					break;
				}
			}
		}
		
		for(Grade studentPassedCourses : this.student.getPassed()) {
			for(CourseClass cs : classes) {
				if(cs.getCode() == studentPassedCourses.getCourse().getCode()) {
					classes.remove(cs);
					break;
				}
			}
		}
		
		Iterator<CourseClass> it = classes.iterator();
		while(it.hasNext()) {
			if(this.student.getCurryear() < it.next().getYear()) it.remove();
		}
		
		if(classes.size() <= 0) {
			JOptionPane.showMessageDialog(null, getWord("noSubjectsAvailable"), getWord("jopUpozorenje"), JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		ArrayList<String> strClasses = new ArrayList<String>();
		for(CourseClass cs : classes) {
			strClasses.add(Short.toString(cs.getCode()) + " - " + cs.getName());
		}
		
		// reference: https://stackoverflow.com/questions/3269516/java-arraylists-into-jlist
		DefaultListModel<String> defaultModelClasses = new DefaultListModel<String>();
		for(String str : strClasses) {
			defaultModelClasses.addElement(str);
		}
		
		JList<String> listOfSubjects = new JList<String>(defaultModelClasses);
		
		JScrollPane scrollPane = new JScrollPane(listOfSubjects, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(scroll_dim);
		add(scrollPane, BorderLayout.CENTER);
		panScroll.add(scrollPane);
		
		//Dugmad
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton confirmButton = new JButton(getWord("buttonFailed1"));
        confirmButton.setPreferredSize(button_dim);
        confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Pre std: " + Add_Subject_To_Student.this.student.getName());
				for(CourseClass cs : Add_Subject_To_Student.this.student.getFailed())
					System.out.println(cs.getName());
				
				int[] selectedIndices = listOfSubjects.getSelectedIndices();
				
				for(int selectedIndex : selectedIndices) {
					CourseClass selectedCourse = classes.get(selectedIndex);
					int idx = 0;
					for(CourseClass cs : classesDB) {
						if(cs.getCode() == selectedCourse.getCode()) {
							break;
						}
						idx++;
					}
					
					CourseClassController.getInstance().AddCourseToStudent(idx, Add_Subject_To_Student.this.student.getIdx());
				}
				System.out.println("Posle: " + Add_Subject_To_Student.this.student.getName());
				for(CourseClass cs : Add_Subject_To_Student.this.student.getFailed())
					System.out.println(cs.getName());
				
				dispose();
			}
		});
        
        
        
        JButton cancelButton = new JButton(getWord("buttonsCancel"));
        cancelButton.setPreferredSize(button_dim);
        cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
        
        buttonsPanel.add(confirmButton);
        buttonsPanel.add(Box.createHorizontalStrut(25));
        buttonsPanel.add(cancelButton);
        add(buttonsPanel, BorderLayout.SOUTH);
        
        panMain.add(Box.createVerticalStrut(20));
        panMain.add(panScroll);
        panMain.add(buttonsPanel);
        
        add(panMain);
        setVisible(true);
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
	
}
