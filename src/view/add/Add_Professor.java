package view.add;

import java.awt.BorderLayout;
import java.awt.Color;
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
import model_sistema.Adress;
import model_sistema.Date;
import model_sistema.Professor;
import model_sistema.Title;
import utility.Parser;
import view.main_frame.Main_Window_Tabs;
import view.main_frame.Main_window;

public class Add_Professor extends JDialog {

	private static final long serialVersionUID = -2670900112487666791L;
	
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
	
	private JLabel label_ime;
	private JLabel label_prezime;
	private JLabel label_datum;
	private JLabel label_adresa;
	private JLabel label_telefon;
	private JLabel label_mail;
	private JLabel label_kancelarija;
	private JLabel label_licna;
	private JLabel label_zvanje;
	private JLabel label_staz;
	
	private JButton potvrdi = new JButton(getWord("buttonsConfirm"));
	private JButton odustani = new JButton(getWord("buttonsCancel"));
	
	public Add_Professor() {
		
		setTitle(getWord("titleAddProfessor"));
		setSize(600, 600);
		setLocationRelativeTo(Main_Window_Tabs.getInstance());
		setModal(true);
		
		//window listener za setVisible(false)
		
		// window main panel
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
		
		label_ime = new JLabel(getWord("nameAddStudent"));
		JTextField field_ime = new JTextField();
		
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
		
		label_prezime = new JLabel(getWord("surnameAddStudent"));
		JTextField field_prezime = new JTextField();
		
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
		
		label_datum = new JLabel(getWord("bdateAddStudent"));
		JTextField field_datum = new JTextField();
		
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
		
		label_adresa = new JLabel(getWord("adressAddStudent"));
		JTextField field_adresa = new JTextField();
		
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
		
		label_telefon = new JLabel(getWord("numAddStudent"));
		JTextField field_telefon = new JTextField();
		
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
		
		label_mail = new JLabel(getWord("mailAddStudent"));
		JTextField field_mail = new JTextField();
		
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
		
		label_kancelarija = new JLabel(getWord("officeNumberAddProfessor"));
		JTextField field_kancelarija = new JTextField();
		
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
		
		label_licna = new JLabel(getWord("idNumAddProfessor"));
		JTextField field_licna = new JTextField();
		
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
					if(prof.getIDnum() == Integer.parseInt(professorLicna)) {
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
		
		label_zvanje = new JLabel(getWord("titleTAddProfessor"));
		
		String[] zvanja = {getWord("associatePAddProfessor"), getWord("professorAddProfessor"), getWord("academicAddProfessor")};
		JComboBox<String> field_zvanje = new JComboBox<String>(zvanja);
		
		label_zvanje.setPreferredSize(label_dimensions);
		field_zvanje.setPreferredSize(label_dimensions);
		field_zvanje.setSelectedIndex(0);
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
		
		label_staz = new JLabel(getWord("serviceYearsAddProfessor"));
		JTextField field_staz = new JTextField();
		
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
		
		potvrdi.setEnabled(false);
		
		potvrdi.setPreferredSize(button_dimensions);
		potvrdi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ProfessorController.getInstance().AddProfessor(professorIme, professorPrezime, datumProfessor, adresaProfessor, professorTelefon, professorMail, professorKancelarija, Integer.parseInt(professorLicna),  titulaProfessor, Integer.parseInt(professorStaz));
				dispose();
			}
	
		});
		
		odustani.setPreferredSize(button_dimensions);
		odustani.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_buttons.add(potvrdi);
		panel_buttons.add(odustani);
	
		add(main_panel, BorderLayout.CENTER);
		setVisibility(false);
	}
	
	private void enableConfirm() {
		if(professorIme == null || professorPrezime == null || datumProfessor == null || adresaProfessor == null || professorTelefon == null || professorMail == null || professorKancelarija == null || professorLicna == null || titulaProfessor == null || professorStaz == null)
			potvrdi.setEnabled(false);
		
		else potvrdi.setEnabled(true);
	}
	
	public void setVisibility(boolean bool) {
		setVisible(bool);
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
}
