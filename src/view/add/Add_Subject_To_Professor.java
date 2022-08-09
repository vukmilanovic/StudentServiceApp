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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import controller.CourseClassController;
import data_base.CourseClassDB;
import model_sistema.CourseClass;
import model_sistema.Professor;
import view.main_frame.Main_window;

import javax.swing.JDialog;

public class Add_Subject_To_Professor extends JDialog {

	private Professor professor;
	private JButton cancelButton = new JButton(getWord("buttonsCancel"));
	private JButton confirmButton = new JButton(getWord("buttonsConfirm"));
	
	public Add_Subject_To_Professor(Professor professor) {
		
		super();
		
		this.professor = professor;
		
		setTitle(getWord("titleAddSubject"));
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
		JLabel labName = new JLabel(getWord("subjectsAddSToP"));
		
		panName.add(Box.createHorizontalStrut(15));
		panName.add(labName);
		panName.add(Box.createGlue());
		
		
		//panel za scroll prozor
		JPanel panScroll = new JPanel();
		
		// bespotrebna kolicina mentalne gimnastike samo zbog jebene jave :)))))
		ArrayList<CourseClass> classesDB = (ArrayList<CourseClass>) CourseClassDB.getInstance().getClasses();
		ArrayList<CourseClass> classes = new ArrayList<CourseClass>(classesDB); // kopija da ne bih brisao bazu
		
		for(CourseClass pcs : this.professor.getCourses()) {
			for(CourseClass cs : classes) {
				if(cs.getCode() == pcs.getCode()) {
					classes.remove(cs);
					break;
				}
			}
		}
		
		// zaboravio sam da iteratori postoje i ovde :) ali sigurno necu prepisivati sve remove-ove
		// ref: https://stackoverflow.com/questions/13316629/remove-objects-from-an-arraylist-based-on-a-given-criteria
		Iterator<CourseClass> it = classes.iterator();
		while(it.hasNext()) {
			if(it.next().getProf() != null) it.remove();
		}
		
		if(classes.size() <= 0) {
			JOptionPane.showMessageDialog(null, getWord("subjectsMessageAddSToP"), getWord("jopUpozorenje"), JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		ArrayList<String> StrClasses = new ArrayList<String>();
		for(CourseClass cs : classes)
			StrClasses.add(Short.toString(cs.getCode()) + " - " + cs.getName());
		
		// reference: https://stackoverflow.com/questions/3269516/java-arraylists-into-jlist
		DefaultListModel<String> ModelClasses = new DefaultListModel<String>();
		for(String str : StrClasses)
			ModelClasses.addElement(str);
		
		JList<String> listOfSubjects = new JList<String>(ModelClasses);
		
		JScrollPane scrollPane = new JScrollPane(listOfSubjects, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(scroll_dim);
        add(scrollPane,BorderLayout.CENTER);
        panScroll.add(scrollPane);
        
        
        //Dugmad
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        confirmButton.setPreferredSize(button_dim);
        confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int[] SelectedIndices = listOfSubjects.getSelectedIndices();
				
				for(int SelectedIdx : SelectedIndices) {
					CourseClass SelectedCourse = classes.get(SelectedIdx);
					System.out.println(SelectedCourse.getName());
					int idx = 0;
					for(CourseClass cs : classesDB) {
						if(cs.getCode() == SelectedCourse.getCode())
							break;
						idx++;
					}
					
					CourseClassController.getInstance().AddCourseToProfessor(idx, Add_Subject_To_Professor.this.professor.getIDnum());
				}
				dispose();
			}
		});

        cancelButton.setPreferredSize(button_dim);
        cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
