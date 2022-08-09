package view.edit;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import controller.StudentController;
import data_base.StudentDB;
import model_sistema.Student;
import view.main_frame.Main_Window_Tabs;
import view.main_frame.Main_window;

public class Edit_Student extends JDialog {
	
	private static final long serialVersionUID = -5784218987433400113L;
	private Student selectedStudent;
	private Edit_Student_Tabs edit_st; 
	
	public Edit_Student(Student selectedStudent){
		
		super();
		
		setTitle(getWord("titleEditStudent"));
		setSize(650,550);
		setLocationRelativeTo(Main_Window_Tabs.getInstance());
		setModal(true);
		
		this.selectedStudent = selectedStudent;
		
		edit_st = new Edit_Student_Tabs(selectedStudent);
		add(edit_st, BorderLayout.CENTER);
		edit_st.getConfirmButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int idx = 0;
				for(Student s : StudentDB.getInstance().getStudent_list()) {
					if(s.getIdx().equals(selectedStudent.getIdx()))
						break;
					idx++;
				}
				
				StudentController.getInstance().EditStudent(idx, edit_st.getStudentIme(), edit_st.getStudentPrezime(), edit_st.getStudentDate(), edit_st.getStudentAdr(), edit_st.getStudentTel(), edit_st.getStudentMail(), edit_st.getStudentIndex(), edit_st.getStudentEnroll(), edit_st.getStudentCurrent(), edit_st.getStudentStatus());
				dispose();
			}
		});
		
		edit_st.getCancelButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		setVisibility(false);
	}
	
	public void setVisibility(boolean bool) {
		setVisible(bool);
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
}
