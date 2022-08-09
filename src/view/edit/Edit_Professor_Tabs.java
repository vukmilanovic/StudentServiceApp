package view.edit;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Flow;

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
import data_base.CourseClassDB;
import data_base.ProfessorDB;
import model_sistema.Adress;
import model_sistema.CourseClass;
import model_sistema.Date;
import model_sistema.Professor;
import model_sistema.Title;
import utility.Parser;
import view.add.Add_Subject_To_Professor;
import view.main_frame.Main_window;
import view.tables.AbstractPassedSubjectsTableModel;
import view.tables.AbstractProfessorSubjectsTableModel;
import view.tables.PassedSubjects;
import view.tables.ProfessorSubjects;

public class Edit_Professor_Tabs extends JTabbedPane {
	
	private Professor selectedProfessor;

	private JButton ConfirmButton = new JButton(getWord("buttonsConfirm"));
	private JButton CancelButton  = new JButton(getWord("buttonsCancel"));

	private String professorIme = null;
	private String professorPrezime = null;
	private Date datumProfessor = null;
	private Adress adresaProfessor = null;
	private String professorTelefon = null;
	private String professorMail = null;
	private String professorKancelarija = null;
	private String professorLicna = null;
	private Title titulaProfessor = null;
	private String professorStaz = null;
	
	private static ProfessorSubjects tableOfProfessorSubjects;

	public Edit_Professor_Tabs(Professor selectedProfessor) {
		
		super();
		
		this.selectedProfessor = selectedProfessor;
		
		//######################################################
		JPanel main_panel = new JPanel();
		BoxLayout main_panel_layout = new BoxLayout(main_panel, BoxLayout.Y_AXIS);
		main_panel.setLayout(main_panel_layout);

		Dimension label_dimensions = new Dimension(200, 30);
		Dimension button_dimensions = new Dimension(100, 30);
		
		// Ime
		JPanel panel_ime = new JPanel();
		main_panel.add(Box.createVerticalStrut(25));
		main_panel.add(panel_ime);
		main_panel.add(Box.createVerticalStrut(2));
		
		JLabel label_ime = new JLabel(getWord("nameAddStudent"));
		JTextField field_ime = new JTextField(this.selectedProfessor.getName());
		
		label_ime.setPreferredSize(label_dimensions);
		field_ime.setPreferredSize(label_dimensions);
		field_ime.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				professorIme = field_ime.getText();
				if(professorIme.matches(".*\\d.*")) {
					professorIme = null;
				}
				enableConfirm();
			}
		});
	
		// default je flow tako da nema nekog cimanja
		panel_ime.add(label_ime);
		panel_ime.add(field_ime);
		
		// Prezime
		JPanel panel_prezime = new JPanel();
		main_panel.add(panel_prezime);
		main_panel.add(Box.createVerticalStrut(2));
		
		JLabel label_prezime = new JLabel(getWord("surnameAddStudent"));
		JTextField field_prezime = new JTextField(this.selectedProfessor.getSurname());
		
		label_prezime.setPreferredSize(label_dimensions);
		field_prezime.setPreferredSize(label_dimensions);
		field_prezime.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				professorPrezime = field_prezime.getText();
				if(professorPrezime.matches(".*\\d.*")) {
					professorPrezime = null;
				}
				enableConfirm();
			}
		});
		
		panel_prezime.add(label_prezime);
		panel_prezime.add(field_prezime);
		
		// Datum
		JPanel panel_datum = new JPanel();
		main_panel.add(panel_datum);
		main_panel.add(Box.createVerticalStrut(2));
		
		JLabel label_datum = new JLabel(getWord("bdateAddStudent"));
		JTextField field_datum = new JTextField(this.selectedProfessor.getDob().toString());
		
		label_datum.setPreferredSize(label_dimensions);
		field_datum.setPreferredSize(label_dimensions);
		field_datum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String professorDatum = field_datum.getText();
				datumProfessor = Parser.parseDateFromString(professorDatum);
				enableConfirm();
			}
		});
		
		panel_datum.add(label_datum);
		panel_datum.add(field_datum);
		
		// Adresa
		JPanel panel_adresa = new JPanel();
		main_panel.add(panel_adresa);
		main_panel.add(Box.createVerticalStrut(2));
		
		JLabel label_adresa = new JLabel(getWord("adressAddStudent"));
		JTextField field_adresa = new JTextField(this.selectedProfessor.getAdr().toString());
		
		label_adresa.setPreferredSize(label_dimensions);
		field_adresa.setPreferredSize(label_dimensions);
		field_adresa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String professorAdresa = field_adresa.getText();
				adresaProfessor = Parser.parseAdressFromString(professorAdresa);
				enableConfirm();
			}
		});
		
		panel_adresa.add(label_adresa);
		panel_adresa.add(field_adresa);

		// Telefon
		JPanel panel_telefon = new JPanel();
		main_panel.add(panel_telefon);
		main_panel.add(Box.createVerticalStrut(2));
		
		JLabel label_telefon = new JLabel(getWord("numAddStudent"));
		JTextField field_telefon = new JTextField(this.selectedProfessor.getTel());
		
		label_telefon.setPreferredSize(label_dimensions);
		field_telefon.setPreferredSize(label_dimensions);
		field_telefon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				professorTelefon = field_telefon.getText();
				if(professorTelefon.matches("[a-zA-Z]+")) {
					professorTelefon = null;
				}
				enableConfirm();
			}
		});
		
		panel_telefon.add(label_telefon);
		panel_telefon.add(field_telefon);
		
		// Mail
		JPanel panel_mail = new JPanel();
		main_panel.add(panel_mail);
		main_panel.add(Box.createVerticalStrut(2));
		
		JLabel label_mail = new JLabel(getWord("mailAddStudent"));
		JTextField field_mail = new JTextField(this.selectedProfessor.getMail());
		
		label_mail.setPreferredSize(label_dimensions);
		field_mail.setPreferredSize(label_dimensions);
		field_mail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				professorMail = field_mail.getText();
				professorMail = Parser.parseEmailFromString(professorMail);
				enableConfirm();
			}
		});
		
		panel_mail.add(label_mail);
		panel_mail.add(field_mail);

		// Kancelarija
		JPanel panel_kancelarija = new JPanel();
		main_panel.add(panel_kancelarija);
		main_panel.add(Box.createVerticalStrut(2));
		
		JLabel label_kancelarija = new JLabel(getWord("officeNumberAddProfessor"));
		JTextField field_kancelarija = new JTextField(this.selectedProfessor.getOffice());
		
		label_kancelarija.setPreferredSize(label_dimensions);
		field_kancelarija.setPreferredSize(label_dimensions);
		field_kancelarija.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				professorKancelarija = field_kancelarija.getText();
				enableConfirm();
			}
		});
		
		panel_kancelarija.add(label_kancelarija);
		panel_kancelarija.add(field_kancelarija);
		
		// Licna
		JPanel panel_licna = new JPanel();
		main_panel.add(panel_licna);
		main_panel.add(Box.createVerticalStrut(2));
		
		JLabel label_licna = new JLabel(getWord("idNumAddProfessor"));
		JTextField field_licna = new JTextField(String.valueOf(this.selectedProfessor.getIDnum()));
		
		label_licna.setPreferredSize(label_dimensions);
		field_licna.setPreferredSize(label_dimensions);
		field_licna.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				professorLicna = field_licna.getText();
				if(professorLicna.matches("[a-zA-Z]+")) {
					professorLicna = null;
				}
				for(Professor prof : ProfessorDB.getInstance().getProf_list()) {
					if(prof.getIDnum() == Integer.parseInt(professorLicna) && !(String.valueOf(selectedProfessor.getIDnum()).equals(professorLicna))) {
						professorLicna = null;
						break;
					}
				}
				enableConfirm();
			}
		});
		
		panel_licna.add(label_licna);
		panel_licna.add(field_licna);

		// Zvanje
		JPanel panel_zvanje = new JPanel();
		main_panel.add(panel_zvanje);
		main_panel.add(Box.createVerticalStrut(2));
		
		JLabel label_zvanje = new JLabel(getWord("titleTAddProfessor"));
		
		String[] zvanja = {getWord("associatePAddProfessor"), getWord("professorAddProfessor"), getWord("academicAddProfessor")};
		JComboBox<String> field_zvanje = new JComboBox<String>(zvanja);
		
		label_zvanje.setPreferredSize(label_dimensions);
		field_zvanje.setPreferredSize(label_dimensions);
		field_zvanje.setSelectedIndex(this.selectedProfessor.getT().ordinal());
		field_zvanje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String professorZvanje = zvanja[field_zvanje.getSelectedIndex()];
				titulaProfessor = Parser.parseTitleFromString(professorZvanje);
				enableConfirm();
			}
		});
		
		panel_zvanje.add(label_zvanje);
		panel_zvanje.add(field_zvanje);
		
		// Staz
		JPanel panel_staz = new JPanel();
		main_panel.add(panel_staz);
		main_panel.add(Box.createVerticalStrut(2));
		
		JLabel label_staz = new JLabel(getWord("serviceYearsAddProfessor"));
		JTextField field_staz = new JTextField(String.valueOf(this.selectedProfessor.getServiceyrs()));
		
		label_staz.setPreferredSize(label_dimensions);
		field_staz.setPreferredSize(label_dimensions);
		field_staz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				professorStaz = field_staz.getText();
				if(professorStaz.matches("[a-zA-Z]+")) {
					professorStaz = null;
				}
				enableConfirm();
			}
		});
		
		panel_staz.add(label_staz);
		panel_staz.add(field_staz);
		
		// Dugmici
		JPanel panel_buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 20));
		main_panel.add(panel_buttons);
		
		ConfirmButton.setEnabled(false);
		
		ConfirmButton.setPreferredSize(button_dimensions);
		CancelButton.setPreferredSize(button_dimensions);
		
		panel_buttons.add(ConfirmButton);
		panel_buttons.add(CancelButton);
		
		//###########################################################
		
		//dimenzije dugmica
		Dimension dimButtons = new Dimension(140, 25);
		
		//dimenzije tabela
		Dimension tables_dim = new Dimension(520, 300);
		
		//###########################################################
		
		JPanel panSubjects = new JPanel();
		BoxLayout boxSubjects = new BoxLayout(panSubjects, BoxLayout.Y_AXIS);
		panSubjects.setLayout(boxSubjects);
		
		//dugmici
		JPanel panButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
		
		JButton buttonSubj1 = new JButton(getWord("buttonSubj1"));
		buttonSubj1.setPreferredSize(dimButtons);
		
		//List<CourseClass> subjectList = CourseClassDB.getInstance().getClasses();
		//subjectList.removeAll(subjectList);
		
		buttonSubj1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				Add_Subject_To_Professor add_subject_to_professor = new Add_Subject_To_Professor(selectedProfessor);
			}
		});
		
		JButton buttonSubj2 = new JButton(getWord("buttonSubj2"));
		buttonSubj2.setPreferredSize(dimButtons);
		buttonSubj2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tableOfProfessorSubjects.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, getWord("jopSubjectMessage"), getWord("jopUpozorenje"), JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(JOptionPane.showConfirmDialog(null, getWord("jopSubjectRemove"), getWord("jopSubjectRemoveTitle"), JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION) {
					System.out.println("Dugme");
					System.out.println(Edit_Professor_Tabs.this.selectedProfessor.getCourses().size());
					
					short code = (short) tableOfProfessorSubjects.getValueAt(tableOfProfessorSubjects.getSelectedRow(), 0);
					int idx = 0;
					ArrayList<CourseClass> cs_list = (ArrayList<CourseClass>) CourseClassDB.getInstance().getClasses();
					for(CourseClass c : cs_list) {
						if(c.getCode() == code) break;
						idx++;
					}
					
					CourseClassController.getInstance().RemoveCourseFromProfessor(idx, Edit_Professor_Tabs.this.selectedProfessor.getIDnum());
					
					System.out.println(Edit_Professor_Tabs.this.selectedProfessor.getCourses().size());
					System.out.println("Kraj Dugme");
				}
			}
		});
		
		panButtons.add(Box.createHorizontalStrut(10));
		panButtons.add(buttonSubj1);
		panButtons.add(buttonSubj2);
		
		//tabela (treba izmeniti u tabelu)
		JPanel panTable = new JPanel();
					
		JPanel panProfessorSubjects = createProfessorSubjectsTablePanel();
		panProfessorSubjects.setPreferredSize(tables_dim);
						
		panTable.add(panProfessorSubjects, BorderLayout.NORTH);
				
		//prazan prostor
		JPanel panEmpty = new JPanel();
		
		//dodavanje u panSubjects
		
		panSubjects.add(Box.createVerticalStrut(40));
		panSubjects.add(panButtons);
		panSubjects.add(panTable);
		//panSubjects.add(Box.createVerticalStrut(55));
		
		//###########################################################
		
		addTab(getWord("editStudentTab0"), main_panel);
		addTab(getWord("tabPredmeti"), panSubjects);
		updateProfessorSubjectsView(null, -1);
		
		setVisible(true);
	}
	
	private void enableConfirm() {
		if(professorIme == null || professorPrezime == null || datumProfessor == null || adresaProfessor == null || professorTelefon == null || professorMail == null || professorKancelarija == null || professorLicna == null || titulaProfessor == null || professorStaz == null)
			ConfirmButton.setEnabled(false);
		
		else ConfirmButton.setEnabled(true);
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
	
	public static void updateProfessorSubjectsView(String action, int value) {
		AbstractProfessorSubjectsTableModel model = (AbstractProfessorSubjectsTableModel) tableOfProfessorSubjects.getModel();
		model.fireTableDataChanged();
//		validate();
	}
	
	public JPanel createProfessorSubjectsTablePanel() {
		tableOfProfessorSubjects = new ProfessorSubjects();
		
		JScrollPane scrollPane = new JScrollPane(tableOfProfessorSubjects);
		JPanel professorSubjectsPanel = new JPanel();
		professorSubjectsPanel.setLayout(new BorderLayout());
		professorSubjectsPanel.add(scrollPane, BorderLayout.CENTER);
		
		return professorSubjectsPanel;
	}
	
	public JButton getConfirmButton() {
		return ConfirmButton;
	}
	
	public JButton getCancelButton() {
		return CancelButton;
	}
	
	public String getProfessorIme() {
		return professorIme;
	}

	public String getProfessorPrezime() {
		return professorPrezime;
	}

	public Date getDatumProfessor() {
		return datumProfessor;
	}

	public Adress getAdresaProfessor() {
		return adresaProfessor;
	}

	public String getProfessorTelefon() {
		return professorTelefon;
	}

	public String getProfessorMail() {
		return professorMail;
	}

	public String getProfessorKancelarija() {
		return professorKancelarija;
	}

	public String getProfessorLicna() {
		return professorLicna;
	}

	public Title getTitulaProfessor() {
		return titulaProfessor;
	}

	public String getProfessorStaz() {
		return professorStaz;
	}

	public static JTable getTableOfProfessorSubjects() {
		return tableOfProfessorSubjects;
	}
	
}
