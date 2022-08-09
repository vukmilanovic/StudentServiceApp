package view.edit;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CourseChairController;
import controller.ProfessorController;
import data_base.CourseChairDB;
import data_base.CourseClassDB;
import data_base.ProfessorDB;
import model_sistema.CourseChair;
import model_sistema.CourseClass;
import model_sistema.Professor;
import view.add.Add_Professor_To_Chair_Head;
import view.main_frame.Main_window;

public class Edit_Department extends JDialog {

	private JButton ConfirmButton = new JButton(getWord("buttonsConfirm"));
	private JButton CancelButton = new JButton(getWord("buttonsCancel"));
	
	private String departmentCode;
	private String departmentName;
	private Professor chairMan;
	
	private CourseChair selectedCourseChair;
	
	public static boolean OdustaniFlag = false;
	
	public Edit_Department(CourseChair selectedCourseChair) {
		
		super();
		
		this.selectedCourseChair = selectedCourseChair;
		
		setTitle(getWord("editDepartmentTitle"));
		setSize(450, 400);
		setLocationRelativeTo(null);
		setModal(true);
		
		//main panel
		JPanel panMain = new JPanel();
		BoxLayout boxMain=new BoxLayout(panMain, BoxLayout.Y_AXIS);
		panMain.setLayout(boxMain);
				
		//dimenzije labela i textfield-ova
		Dimension dimension_txt = new Dimension(220, 30);
		Dimension dimension_lab = new Dimension(120,30);	
				
		//dimenzije dugmadi
		Dimension dim_button = new Dimension(90, 30);
		Dimension dim_button2 = new Dimension(42,30);
	
				
		//Sifra
		JPanel panID = new JPanel();
		JLabel labID = new JLabel(getWord("codeAddSubject"));
		labID.setPreferredSize(dimension_lab);
						
		JTextField txtID = new JTextField(String.valueOf(this.selectedCourseChair.getCode()));
		txtID.setPreferredSize(dimension_txt);
		txtID.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				departmentCode = txtID.getText();
				if(departmentCode.matches("[a-zA-Z]+")) {
					departmentCode = null;
				}
				for(CourseChair chair : CourseChairDB.getInstance().getChairs()) {
					if(chair.getCode() == Integer.parseInt(departmentCode) && !(String.valueOf(chair.getCode()).equals(departmentCode))) {
						departmentCode = null;
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
		JLabel labName = new JLabel(getWord("nameEditDepartment"));
		labName.setPreferredSize(dimension_lab);
						
		JTextField txtName = new JTextField(this.selectedCourseChair.getName());
		txtName.setPreferredSize(dimension_txt);
		txtName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				departmentName = txtName.getText();
				enableConfirm();
			}
		});
				
		panName.add(labName);
		panName.add(txtName);
				
		//chairman
		JPanel panChairMan = new JPanel();
				
		JButton removeButton = new JButton("-");
		removeButton.setPreferredSize(dim_button2);
		if(this.selectedCourseChair.getHead() == null)
			removeButton.setEnabled(false);
				
		JLabel labChairMan = new JLabel(getWord("chairmanEditDepartment"));
		labChairMan.setPreferredSize(dimension_lab);
				
		JTextField txtChairMan = new JTextField();
		txtChairMan.setPreferredSize(new Dimension(130, 30));
		if(this.selectedCourseChair.getHead() != null)
			txtChairMan.setText(this.selectedCourseChair.getHead().getName() + " " + this.selectedCourseChair.getHead().getSurname());
		txtChairMan.setEditable(false);
				
		JButton addButton = new JButton("+");
		addButton.setPreferredSize(dim_button2);
		if(this.selectedCourseChair.getHead() != null)
			addButton.setEnabled(false);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Add_Professor_To_Chair_Head add_professor_to_chair_head = new Add_Professor_To_Chair_Head(Edit_Department.this.selectedCourseChair);
				if(!OdustaniFlag) {
					txtChairMan.setText(Edit_Department.this.selectedCourseChair.getHead().getName() + " " + Edit_Department.this.selectedCourseChair.getHead().getSurname());
					addButton.setEnabled(false);
					removeButton.setEnabled(true);
				}
			}
		});
		
		removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = 0;
				for(CourseChair chair : CourseChairDB.getInstance().getChairs()) {
					if(chair.getCode() == Edit_Department.this.selectedCourseChair.getCode())
						break;
					idx++;
				}
				
				ProfessorController.getInstance().RemoveProfessorFromCourseChairHead(idx);
				txtChairMan.setText("");
				addButton.setEnabled(true);
				removeButton.setEnabled(false);
			}
		});
				
		panChairMan.add(labChairMan);
		panChairMan.add(txtChairMan);
		panChairMan.add(addButton);
		panChairMan.add(removeButton);
		
		//professors
		JPanel panProfessors = new JPanel();
		
		JButton addPButton = new JButton("+");
		addPButton.setPreferredSize(new Dimension(50, 30));
		
		JButton removePButton = new JButton("-");
		removePButton.setPreferredSize(new Dimension(50, 30));
		
		JLabel labProfessors = new JLabel(getWord("professorsEditDepartment"));
		labProfessors.setPreferredSize(new Dimension(200, 30));
		
		panProfessors.add(labProfessors);
		panProfessors.add(Box.createHorizontalStrut(15));
		panProfessors.add(addPButton);
		panProfessors.add(Box.createHorizontalStrut(10));
		panProfessors.add(removePButton);
		
		//dugmad
		JPanel panButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 20));
				
		ConfirmButton.setPreferredSize(dim_button);
		ConfirmButton.setEnabled(false);
		ConfirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx = 0;
				for(CourseChair chair : CourseChairDB.getInstance().getChairs()) {
					if(chair.getCode() == Edit_Department.this.selectedCourseChair.getCode())
						break;
					idx++;
				}
				
				CourseChairController.getInstance().EditCourseChair(idx, Integer.parseInt(departmentCode), departmentName, chairMan);
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
		
				
		//dodavanje na glavni panel
				
		panMain.add(Box.createVerticalStrut(25));
		panMain.add(panID);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panName);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panChairMan);
		panMain.add(Box.createVerticalStrut(2));
		panMain.add(panProfessors);
		panMain.add(Box.createVerticalStrut(5));
		panMain.add(panButtons);
				
		add(panMain, BorderLayout.CENTER);
		setVisibility(false);
		
	}
	
	public void enableConfirm() {
		if(departmentCode == null || departmentName == null)
			ConfirmButton.setEnabled(false);
		else
			ConfirmButton.setEnabled(true);
	}
	
	public void setVisibility(boolean bool) {
		setVisible(bool);
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
	
}
