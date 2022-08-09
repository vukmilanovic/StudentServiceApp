package view.edit;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ProfessorController;
import data_base.ProfessorDB;
import model_sistema.Professor;
import view.main_frame.Main_Window_Tabs;
import view.main_frame.Main_window;

public class Edit_Professor extends JDialog {
	
	private static final long serialVersionUID = 7230370151604518411L;
	private Professor selectedProfessor;
	private Edit_Professor_Tabs edit_pt;
	
	// polje klase koje ce se svuda provlaciti
	
	public Edit_Professor(Professor selectedProfessor) {
		
		super();
		
		this.selectedProfessor = selectedProfessor;
		
		setTitle(getWord("titleEditProfessor"));
		setSize(650, 550);
		setLocationRelativeTo(Main_Window_Tabs.getInstance());
		setModal(true);
		
		edit_pt = new Edit_Professor_Tabs(selectedProfessor);
		add(edit_pt, BorderLayout.CENTER);
		edit_pt.getConfirmButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int idx = 0;
				for(Professor p : ProfessorDB.getInstance().getProf_list()) {
					if(p.getIDnum() == Edit_Professor.this.selectedProfessor.getIDnum())
						break;
					idx++;
				}
				
				ProfessorController.getInstance().EditProfessor(idx, edit_pt.getProfessorIme(), edit_pt.getProfessorPrezime(), edit_pt.getDatumProfessor(), edit_pt.getAdresaProfessor(), edit_pt.getProfessorTelefon(), edit_pt.getProfessorMail(), edit_pt.getProfessorKancelarija(), Integer.parseInt(edit_pt.getProfessorLicna()),  edit_pt.getTitulaProfessor(), Integer.parseInt(edit_pt.getProfessorStaz()));
				setVisible(false);
			}
		});
		
		edit_pt.getCancelButton().addActionListener(new ActionListener() {
			
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
