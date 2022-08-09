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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controller.CourseClassController;
import data_base.CourseClassDB;
import data_base.ProfessorDB;
import model_sistema.CourseClass;
import model_sistema.Professor;
import view.edit.Edit_Professor_Tabs;
import view.edit.Edit_Subject;
import view.main_frame.Main_window;

public class Add_Professor_To_Subject extends JDialog{
	
	private CourseClass course;
	private JButton confirmButton = new JButton(getWord("buttonsConfirm"));
	private JButton cancelButton = new JButton(getWord("buttonsCancel"));
	
	public Add_Professor_To_Subject(CourseClass course) {
		
		super();
		
		this.course = course;
		
		setTitle(getWord("titleAddPToSubject"));
		setSize(400,300);
		setLocationRelativeTo(null);
		setModal(true);
        
        JPanel panMain = new JPanel();
		BoxLayout boxMain = new BoxLayout(panMain, BoxLayout.Y_AXIS);
		panMain.setLayout(boxMain);
		
		//dimenzije dugmadi
		Dimension button_dim = new Dimension(85 ,25);
        
		//dimenzije scroll prozora
		Dimension scroll_dim = new Dimension(300 ,150);
		
		//panel za naziv
		JPanel panName = new JPanel();
		BoxLayout boxName = new BoxLayout(panName, BoxLayout.X_AXIS);
		panName.setLayout(boxName);
		JLabel labName = new JLabel(getWord("professorsAddPToSubject"));
		
		panName.add(Box.createHorizontalStrut(15));
		panName.add(labName);
		panName.add(Box.createGlue());
		
		
		//panel za scroll prozor
		JPanel panScroll = new JPanel();
		
		ArrayList<Professor> professorsDB = (ArrayList<Professor>) ProfessorDB.getInstance().getProf_list();
		ArrayList<Professor> professors = new ArrayList<Professor>(professorsDB);
		
		if(professors.size() <= 0) {
			JOptionPane.showMessageDialog(null, getWord("professorsMessageAddPToSubject"), getWord("jopUpozorenje"), JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		ArrayList<String> StrProfessors = new ArrayList<String>();
		for(Professor prof : professors)
			StrProfessors.add(prof.getName() + " " + prof.getSurname());
		
		DefaultListModel<String> ModelProfessors = new DefaultListModel<String>();
		for(String str : StrProfessors)
			ModelProfessors.addElement(str);
		
		JList<String> listOfProfessors = new JList<String>(ModelProfessors);
		listOfProfessors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(listOfProfessors, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(scroll_dim);
        add(scrollPane,BorderLayout.CENTER);
        panScroll.add(scrollPane);
        
        
        //Dugmad
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
     
        confirmButton.setPreferredSize(button_dim);
        confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int SelectedIndex = listOfProfessors.getSelectedIndex();
				Professor SelectedProf = professors.get(SelectedIndex);
				
				int idx = 0;
				for(CourseClass cs : CourseClassDB.getInstance().getClasses()) {
					if(cs.getCode() == Add_Professor_To_Subject.this.course.getCode())
						break;
					idx++;
				}
				
				Edit_Subject.OdustaniFlag = false;
				CourseClassController.getInstance().AddCourseToProfessor(idx, SelectedProf.getIDnum());
				dispose();
			}
		});

        cancelButton.setPreferredSize(button_dim);
        cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Edit_Subject.OdustaniFlag = true;
				dispose();
			}
		});
        
        
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(confirmButton);
        buttonsPanel.add(Box.createHorizontalStrut(2));
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(Box.createHorizontalStrut(2));
        add(buttonsPanel, BorderLayout.SOUTH);
        
        panMain.add(Box.createVerticalStrut(5));
        panMain.add(panName);
        panMain.add(Box.createVerticalStrut(5));
        panMain.add(panScroll);
        panMain.add(buttonsPanel);
        
        add(panMain);
		setVisible(true);
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
	
}
