package view.main_frame;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.CourseClassController;
import controller.ProfessorController;
import controller.StudentController;
import data_base.CourseChairDB;
import data_base.CourseClassDB;
import data_base.GradeDB;
import data_base.ProfessorDB;
import data_base.StudentDB;
import model_sistema.CourseChair;
import model_sistema.CourseClass;
import model_sistema.Grade;
import model_sistema.IndexNum;
import model_sistema.Professor;
import model_sistema.Student;
import view.add.Add_Professor;
import view.add.Add_Student;
import view.add.Add_Subject;
import view.edit.Edit_Department;
import view.edit.Edit_Professor;
import view.edit.Edit_Student;
import view.edit.Edit_Subject;

public class MenuBar extends JMenuBar {
	
	private JMenu file;
	private JMenu miOpen;
	private JMenu edit;
	private JMenu help;
	private JMenuItem miNew;
	private JMenuItem miSave;
	private JMenuItem miStudenti;
	private JMenuItem miProfesori;
	private JMenuItem miPredmeti;
	private JMenuItem miKatedre;
	private JMenuItem miClose;
	private JMenuItem miEdit;
	private JMenuItem miHelp;
	private JMenuItem miDelete;
	private JMenuItem miAbout;
	
	private Add_Student as1 = new Add_Student();
	private Add_Professor ap = new Add_Professor();
	private Add_Subject as2 = new Add_Subject();
	private Edit_Student es1;
	private Edit_Professor ep;
	private Edit_Subject es2;
	private Edit_Department ed;
	
	JCheckBoxMenuItem mniSrpski;
	JCheckBoxMenuItem mniEngleski;
	
	public MenuBar() {
		
		file = new JMenu(getWord("mnuFile"));
		file.setMnemonic(KeyEvent.VK_F);
		
		miNew = new JMenuItem(getWord("mniNew"));
		//miNew.setMnemonic(KeyEvent.VK_N);
		
		if(getWord("mniNew").equals("Dodaj")) {
			miNew.setMnemonic(KeyEvent.VK_J);
		} else if(getWord("mniNew").equals("New")) {
			miNew.setMnemonic(KeyEvent.VK_N);
		}
		
		miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		ImageIcon imagei = new ImageIcon("Images/new_menu.png");
		Image img = imagei.getImage();
		Image newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		miNew.setIcon(new ImageIcon(newimg));
		miNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main_Window_Tabs mwt = Main_Window_Tabs.getInstance();
				
				if(mwt.getSelectedIndex() == 0) {
					as1 = new Add_Student();
					as1.setVisibility(true);
				}
				else if(mwt.getSelectedIndex() == 1) {
					ap = new Add_Professor();
					ap.setVisibility(true);
				} 
				else if(mwt.getSelectedIndex() == 2) {
					as2 = new Add_Subject();
					as2.setVisibility(true);
				}
			}
		});
		
		miSave = new JMenuItem(getWord("mniSave"));
		miSave.setMnemonic(KeyEvent.VK_S);
		miSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		imagei = new ImageIcon("Images/save.png");
		img = imagei.getImage();
		newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		miSave.setIcon(new ImageIcon(newimg));
		miSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					StudentDB.getInstance().SaveDataBaseState();
					ProfessorDB.getInstance().SaveDataBaseState();
					CourseClassDB.getInstance().SaveDataBaseState();
					GradeDB.getInstance().SaveDataBaseState();
					CourseChairDB.getInstance().SaveDataBaseState();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		miOpen = new JMenu(getWord("mnuOpen"));
		miOpen.setMnemonic(KeyEvent.VK_O);
		imagei = new ImageIcon("Images/open.png");
		img = imagei.getImage();
		newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		miOpen.setIcon(new ImageIcon(newimg));
		
		miStudenti = new JMenuItem(getWord("mniStudenti"));
		miStudenti.setMnemonic(KeyEvent.VK_T);
		miStudenti.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		imagei = new ImageIcon("Images/Student.jpg");
		img = imagei.getImage();
		newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		miStudenti.setIcon(new ImageIcon(newimg));
		miStudenti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main_Window_Tabs mwt = Main_Window_Tabs.getInstance();
				mwt.setSelectedIndex(0);
			}
		});
		
		miPredmeti = new JMenuItem(getWord("mniPredmeti"));
		if(getWord("mniPredmeti").equals("Predmet")) {
			miPredmeti.setMnemonic(KeyEvent.VK_P);
		} else if(getWord("mniPredmeti").equals("Subject")) {
			miPredmeti.setMnemonic(KeyEvent.VK_U);
		}
		miPredmeti.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		imagei = new ImageIcon("Images/Predmet.png");
		img = imagei.getImage();
		newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		miPredmeti.setIcon(new ImageIcon(newimg));
		miPredmeti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main_Window_Tabs mwt = Main_Window_Tabs.getInstance();
				mwt.setSelectedIndex(2);
			}
		});
		
		miProfesori = new JMenuItem(getWord("mniProfesori"));
		miProfesori.setMnemonic(KeyEvent.VK_R);
		miProfesori.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		imagei = new ImageIcon("Images/Profesor.png");
		img = imagei.getImage();
		newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		miProfesori.setIcon(new ImageIcon(newimg));
		miProfesori.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main_Window_Tabs mwt = Main_Window_Tabs.getInstance();
				mwt.setSelectedIndex(1);
			}
		});
		
		miKatedre = new JMenuItem(getWord("mniKatedre"));
		if(getWord("mniKatedre").equals("Katedra")) {
			miKatedre.setMnemonic(KeyEvent.VK_K);
		} else if(getWord("mniKatedre").equals("Department")) {
			miKatedre.setMnemonic(KeyEvent.VK_M);
		}
		miKatedre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
		imagei = new ImageIcon("Images/Katedra.jpg");
		img = imagei.getImage();
		newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		miKatedre.setIcon(new ImageIcon(newimg));
		miKatedre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main_Window_Tabs mwt = Main_Window_Tabs.getInstance();
				mwt.setSelectedIndex(3);
			}
		});
		
		miOpen.add(miStudenti);
		miOpen.addSeparator();
		miOpen.add(miPredmeti);
		miOpen.addSeparator();
		miOpen.add(miProfesori);
		miOpen.addSeparator();
		miOpen.add(miKatedre);	
		
		miClose = new JMenuItem(getWord("mniClose"));
		if(getWord("mniClose").equals("Zatvori")) {
			miClose.setMnemonic(KeyEvent.VK_Z);
		} else if(getWord("mniClose").equals("Close")) {
			miClose.setMnemonic(KeyEvent.VK_C);
		}
		miClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		imagei = new ImageIcon("Images/close.png");
		img = imagei.getImage();
		newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		miClose.setIcon(new ImageIcon(newimg));
		miClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main_window mw = Main_window.getInstance();
				//predlog zatvaranja jframe-a nadjen na stackoverflow sajtu
				mw.dispose();
				//System.exit(0);
			}
		});
		
		file.add(miNew);
		file.addSeparator();
		file.add(miSave);
		file.addSeparator();
		file.add(miOpen);
		file.addSeparator();
		file.add(miClose);
		
		edit = new JMenu(getWord("mnuEdit"));
		edit.setMnemonic(KeyEvent.VK_E);
		
		miEdit = new JMenuItem(getWord("mniEdit"));
		miEdit.setMnemonic(KeyEvent.VK_E);
		miEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		imagei = new ImageIcon("Images/edit.png");
		img = imagei.getImage();
		newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		miEdit.setIcon(new ImageIcon(newimg));
		miEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main_Window_Tabs mwt = Main_Window_Tabs.getInstance();
				
				if(mwt.getSelectedIndex() == 0) {
					if(Main_Window_Tabs.getInstance().getTableOfStudents().getSelectedRow() < 0) {
						JOptionPane.showMessageDialog(null, getWord("jopStudentMessage"), getWord("jopUpozorenje"), JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					IndexNum index = (IndexNum) Main_Window_Tabs.getInstance().getTableOfStudents().getValueAt(Main_Window_Tabs.getInstance().getTableOfStudents().getSelectedRow(),  0);
					int idx = 0;
					
					for(Student s : StudentDB.getInstance().getStudent_list()) {
						if(s.getIdx().equals(index))
							break;
						idx++;
					}
					
					Student selectedStudent = StudentDB.getInstance().getRow(idx);
					es1 = new Edit_Student(selectedStudent);
					es1.setVisibility(true);
				}
				else if(mwt.getSelectedIndex() == 1) {
					if(Main_Window_Tabs.getInstance().getTableOfProfessors().getSelectedRow() < 0) {
						JOptionPane.showMessageDialog(null, getWord("jopProfessorMessage"), getWord("jopUpozorenje"), JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					String mail = (String) Main_Window_Tabs.getInstance().getTableOfProfessors().getValueAt(Main_Window_Tabs.getInstance().getTableOfProfessors().getSelectedRow(),  3);
					int idx = 0;
					
					for(Professor p : ProfessorDB.getInstance().getProf_list()) {
						if(p.getMail().equals(mail))
							break;
						idx++;
					}
					
					Professor selectedProfessor = ProfessorDB.getInstance().getRow(idx);
					ep = new Edit_Professor(selectedProfessor);
					ep.setVisibility(true);
				} 
				else if(mwt.getSelectedIndex() == 2) {
					if(Main_Window_Tabs.getInstance().getTableOfSubjects().getSelectedRow() < 0) {
						JOptionPane.showMessageDialog(null, getWord("jopSubjectMessage"), getWord("jopUpozorenje"), JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					short code = (short) Main_Window_Tabs.getInstance().getTableOfSubjects().getValueAt(Main_Window_Tabs.getInstance().getTableOfSubjects().getSelectedRow(), 0);
					int idx = 0;
					
					for(CourseClass cs : CourseClassDB.getInstance().getClasses()) {
						if(cs.getCode() == code)
							break;
						idx++;
					}
					
					CourseClass selectedCourseClass = CourseClassDB.getInstance().getRow(idx);
					es2 = new Edit_Subject(selectedCourseClass);
					es2.setVisibility(true);
				} else if(mwt.getSelectedIndex() == 3) {
					if(Main_Window_Tabs.getInstance().getTableOfDepartments().getSelectedRow() < 0) {
						JOptionPane.showMessageDialog(null, getWord("jopDepartmentMessage"), getWord("jopUpozorenje"), JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					int code = (int) Main_Window_Tabs.getInstance().getTableOfDepartments().getValueAt(Main_Window_Tabs.getInstance().getTableOfDepartments().getSelectedRow(), 0);
					int idx = 0;
					
					for(CourseChair cs : CourseChairDB.getInstance().getChairs()) {
						if(cs.getCode() == code)
							break;
						idx++;
					}
					
					CourseChair selectedCourseChair = CourseChairDB.getInstance().getRow(idx);
					ed = new Edit_Department(selectedCourseChair);
					ed.setVisibility(true);
				}
			}
		});
		
		miDelete = new JMenuItem(getWord("mniDelete"));
		if(getWord("mniDelete").equals("Izbriši")) {
			miDelete.setMnemonic(KeyEvent.VK_B);
		} else if(getWord("mniDelete").equals("Delete")) {
			miDelete.setMnemonic(KeyEvent.VK_D);
		}
		miDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		imagei = new ImageIcon("Images/delete.png");
		img = imagei.getImage();
		newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		miDelete.setIcon(new ImageIcon(newimg));
		miDelete.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				Main_Window_Tabs mwt = Main_Window_Tabs.getInstance();
				
				if(mwt.getSelectedIndex() == 0) {
					if(mwt.getTableOfStudents().getSelectedRow() < 0) {
						JOptionPane.showMessageDialog(null, getWord("jopStudentMessage") , getWord("jopUpozorenje"), JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if(JOptionPane.showConfirmDialog(null, getWord("jopStudentDelete"), getWord("jopStudentDeleteTitle"), JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION) {
						int convSelRow = mwt.getTableOfStudents().convertRowIndexToModel(mwt.getTableOfStudents().getSelectedRow());
						StudentController.getInstance().RemoveStudent(convSelRow);
					}
				}
				else if(mwt.getSelectedIndex() == 1) {
					if(mwt.getTableOfProfessors().getSelectedRow() < 0) {
						JOptionPane.showMessageDialog(null, getWord("jopProfessorMessage"), getWord("jopUpozorenje"), JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if(JOptionPane.showConfirmDialog(null, getWord("jopProfessorDelete"), getWord("jopProfessorDeleteTitle"), JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION) {
						int convSelRow = mwt.getTableOfProfessors().convertRowIndexToModel(mwt.getTableOfProfessors().getSelectedRow());
						ProfessorController.getInstance().RemoveProfessor(convSelRow);
					}
				} 
				else if(mwt.getSelectedIndex() == 2) {
					if(mwt.getTableOfSubjects().getSelectedRow() < 0) {
						JOptionPane.showMessageDialog(null, getWord("jopSubjectMessage"), getWord("jopUpozorenje"), JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if(JOptionPane.showConfirmDialog(null, getWord("jopSubjectDelete"), getWord("jopSubjectDeleteTitle"), JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION) {
						int convSelRow = mwt.getTableOfSubjects().convertRowIndexToModel(mwt.getTableOfSubjects().getSelectedRow());
						CourseClassController.getInstance().RemoveCourseClass(convSelRow);
					}
				}
			}
		});
		
		edit.add(miEdit);
		edit.addSeparator();
		edit.add(miDelete);		
		
		help = new JMenu(getWord("mnuHelp"));
		help.setMnemonic(KeyEvent.VK_P);
		
		miHelp = new JMenuItem(getWord("mniHelp"));
		miHelp.setMnemonic(KeyEvent.VK_P);
		miHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		imagei = new ImageIcon("Images/help.jpg");
		img = imagei.getImage();
		newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		miHelp.setIcon(new ImageIcon(newimg));
		miHelp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HelpDialog hd = new HelpDialog();
			}
		});
		
		miAbout = new JMenuItem(getWord("mniAbout"));
		miAbout.setMnemonic(KeyEvent.VK_A);
		miAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		imagei = new ImageIcon("Images/about.png");
		img = imagei.getImage();
		newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		miAbout.setIcon(new ImageIcon(newimg));
		miAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AboutAppDialog aboutApp = new AboutAppDialog();
			}
		});
		
		mniSrpski = new JCheckBoxMenuItem(getWord("mniSrpski"));
		mniSrpski.setMnemonic(KeyEvent.VK_I);
		mniSrpski.setSelected(true);
		mniSrpski.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("sr", "RS"));
				Main_window.getInstance().changeLanguage();
			}
		});
		
		mniEngleski = new JCheckBoxMenuItem(getWord("mniEngleski"));
		mniEngleski.setMnemonic(KeyEvent.VK_G);
		mniEngleski.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en", "US"));
				Main_window.getInstance().changeLanguage();
			}
		});
		
		help.add(mniSrpski);
		help.addSeparator();
		help.add(mniEngleski);
		help.addSeparator();
		help.add(miHelp);
		help.addSeparator();
		help.add(miAbout);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(mniSrpski);
		bg.add(mniEngleski);
		
		add(Box.createHorizontalStrut(15));
		add(file);
		add(edit);
		add(help);
		
	}
	
	public void initComponents() {
		
		file.setText(getWord("mnuFile"));
		miOpen.setText(getWord("mnuOpen"));
		edit.setText(getWord("mnuEdit"));
		help.setText(getWord("mnuHelp"));
		
		miNew.setText(getWord("mniNew"));
		miSave.setText(getWord("mniSave"));
		miDelete.setText(getWord("mniDelete"));
		miAbout.setText(getWord("mniAbout"));
		miEdit.setText(getWord("mniEdit"));
		miHelp.setText(getWord("mniHelp"));
		miClose.setText(getWord("mniClose"));
		miStudenti.setText(getWord("mniStudenti"));
		miProfesori.setText(getWord("mniProfesori"));
		miPredmeti.setText(getWord("mniPredmeti"));
		miKatedre.setText(getWord("mniKatedre"));
		
		mniSrpski.setText(getWord("mniSrpski"));
		mniEngleski.setText(getWord("mniEngleski"));
		
		if(getWord("mniNew").equals("Dodaj")) {
			miNew.setMnemonic(KeyEvent.VK_J);
		} else if(getWord("mniNew").equals("New")) {
			miNew.setMnemonic(KeyEvent.VK_N);
		}
		
		if(getWord("mniDelete").equals("Izbriši")) {
			miDelete.setMnemonic(KeyEvent.VK_B);
		} else if(getWord("mniDelete").equals("Delete")) {
			miDelete.setMnemonic(KeyEvent.VK_D);
		}
		
		if(getWord("mniClose").equals("Zatvori")) {
			miClose.setMnemonic(KeyEvent.VK_Z);
		} else if(getWord("mniClose").equals("Close")) {
			miClose.setMnemonic(KeyEvent.VK_C);
		}
		
		if(getWord("mniKatedre").equals("Katedra")) {
			miKatedre.setMnemonic(KeyEvent.VK_K);
		} else if(getWord("mniKatedre").equals("Department")) {
			miKatedre.setMnemonic(KeyEvent.VK_M);
		}
		
		if(getWord("mniPredmeti").equals("Predmet")) {
			miPredmeti.setMnemonic(KeyEvent.VK_P);
		} else if(getWord("mniPredmeti").equals("Subject")) {
			miPredmeti.setMnemonic(KeyEvent.VK_U);
		}
		
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
	
}
