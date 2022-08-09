package view.main_frame;

import java.awt.Image;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.CourseClassController;
import controller.ProfessorController;
import controller.StudentController;
import data_base.CourseChairDB;
import data_base.CourseClassDB;
import data_base.ProfessorDB;
import data_base.StudentDB;
import model_sistema.CourseChair;
import model_sistema.CourseClass;
import model_sistema.IndexNum;
import model_sistema.Professor;
import model_sistema.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import view.add.Add_Professor;
import view.add.Add_Student;
import view.add.Add_Subject;
import view.edit.Edit_Department;
import view.edit.Edit_Professor;
import view.edit.Edit_Student;
import view.edit.Edit_Subject;

public class ToolBar extends JToolBar {
	
		private JButton btnNewE;
		private JButton btnEditE;
		private JButton btnDeleteE;
		private JButton btnSearchE;
		
		private Add_Student as1;
		private Add_Professor ap;
		private Add_Subject as2;
		private Edit_Student es1;
		private Edit_Professor ep;
		private Edit_Subject es2;
		private Edit_Department ed;
	
		public ToolBar() {
			
			super(SwingConstants.HORIZONTAL);
			
			btnNewE = new JButton();
			btnNewE.setToolTipText(getWord("tooltipNew"));
			ImageIcon imagei = new ImageIcon("Images/new_tool.png");
			Image img = imagei.getImage();
			Image newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
			btnNewE.setIcon(new ImageIcon(newimg));
			btnNewE.addActionListener(new ActionListener() {
				
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
			add(Box.createHorizontalStrut(15));
			add(btnNewE);
			
			addSeparator();
			
			btnEditE = new JButton();
			btnEditE.setToolTipText(getWord("tooltipEdit"));
			imagei = new ImageIcon("Images/edit1.png");
			img = imagei.getImage();
			newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
			btnEditE.setIcon(new ImageIcon(newimg));
			btnEditE.addActionListener(new ActionListener() {
				
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
			add(btnEditE);
			
			addSeparator();
			
			btnDeleteE = new JButton();
			btnDeleteE.setToolTipText(getWord("tooltipDelete"));
			imagei = new ImageIcon("Images/delete1.png");
			img = imagei.getImage();
			newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
			btnDeleteE.setIcon(new ImageIcon(newimg));
			btnDeleteE.addActionListener(new ActionListener() {
				
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
			add(btnDeleteE);
			
			add(Box.createHorizontalGlue());
			
			JTextField txtF = new JTextField(25);
			txtF.setMaximumSize(txtF.getPreferredSize());
			add(txtF);
			txtF.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Main_Window_Tabs mwt = Main_Window_Tabs.getInstance();
					if(mwt.getSelectedIndex() == 0)
						StudentSearchPerformed(e, txtF);
					else if(mwt.getSelectedIndex() == 1)
						ProfessorSearchPerformed(e, txtF);
					else
						SubjectSearchPerformed(e, txtF);
				}
			});
			
			addSeparator();
			
			btnSearchE = new JButton();
			btnSearchE.setToolTipText(getWord("tooltipSearch"));
			imagei = new ImageIcon("Images/search.png");
			img = imagei.getImage();
			newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
			btnSearchE.setIcon(new ImageIcon(newimg));
			add(btnSearchE);
			add(Box.createHorizontalStrut(15));
			btnSearchE.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Main_Window_Tabs mwt = Main_Window_Tabs.getInstance();
					if(mwt.getSelectedIndex() == 0)
						StudentSearchPerformed(e, txtF);
					else if(mwt.getSelectedIndex() == 1)
						ProfessorSearchPerformed(e, txtF);
					else
						SubjectSearchPerformed(e, txtF);
				}
			});
			
			setFloatable(false);
			
		}
		
		public void initComponents() {
			
			btnNewE.setToolTipText(getWord("tooltipNew"));
			btnSearchE.setToolTipText(getWord("tooltipSearch"));
			btnEditE.setToolTipText(getWord("tooltipEdit"));
			btnDeleteE.setToolTipText(getWord("tooltipDelete"));
			
			//as1.initComponenets();
			//Add_professor
			//Add_subject
			
			//Edit_student
			//Edit_professor
			//Edit_subject
			
		}
		
		public String getWord(String str) {
			return Main_window.getInstance().getResourceBundle().getString(str);
		}
		
		private void StudentSearchPerformed(ActionEvent e, JTextField txtF) {
			Main_Window_Tabs mwt = Main_Window_Tabs.getInstance();
			JTable current_table = mwt.getTableOfStudents();

			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>((AbstractTableModel) current_table.getModel());
			List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
			
			// izbaci blank spaces i proveri da li je string prazan
			String text = parse_query(txtF.getText());
			if(text.isBlank() || text.isEmpty()) {
				sorter.setRowFilter(RowFilter.regexFilter(text));
				current_table.setRowSorter(sorter);
				return;
			}
			
			// rastavi query
			String[] query = text.split(", ");
			if(query.length == 1)
				query = text.split(",");
			
			// po duzini radi odredjene stvari
			switch(query.length) {
			case 1: // samo prezime
				sorter.setRowFilter(RowFilter.regexFilter(construct_query(query[0]), 2));
				break;
			case 2: // prezime ime
				filters.add(RowFilter.regexFilter(construct_query(query[0]), 2));
				filters.add(RowFilter.regexFilter(construct_query(query[1]), 1));
				sorter.setRowFilter(RowFilter.andFilter(filters));
				break;
			case 3:
				filters.add(RowFilter.regexFilter(query[0].toUpperCase(), 0));
				filters.add(RowFilter.regexFilter(construct_query(query[1]), 2));
				filters.add(RowFilter.regexFilter(construct_query(query[2]), 1));
				sorter.setRowFilter(RowFilter.andFilter(filters));
				break;
			default:
				break;
			}
			
			current_table.setRowSorter(sorter);
		}
		
		private void ProfessorSearchPerformed(ActionEvent e, JTextField txtF) {
			Main_Window_Tabs mwt = Main_Window_Tabs.getInstance();
			JTable current_table = mwt.getTableOfProfessors();

			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>((AbstractTableModel) current_table.getModel());
			List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
			
			// izbaci blank spaces i proveri da li je string prazan
			String text = parse_query(txtF.getText());
			if(text.isBlank() || text.isEmpty()) {
				sorter.setRowFilter(RowFilter.regexFilter(text));
				current_table.setRowSorter(sorter);
				return;
			}
			
			// rastavi query
			String[] query = text.split(", ");
			if(query.length == 1)
				query = text.split(",");
			
			switch(query.length) {
			case 1: // samo prz
				sorter.setRowFilter(RowFilter.regexFilter(construct_query(query[0]), 1));
				break;
			case 2: // prz pa ime				
				filters.add(RowFilter.regexFilter(construct_query(query[0]), 1));
				filters.add(RowFilter.regexFilter(construct_query(query[1]), 0));
				sorter.setRowFilter(RowFilter.andFilter(filters));
				break;
			default:
				break;
			}
			
			current_table.setRowSorter(sorter);
		}
		
		private void SubjectSearchPerformed(ActionEvent e, JTextField txtF) {
			Main_Window_Tabs mwt = Main_Window_Tabs.getInstance();
			JTable current_table = mwt.getTableOfSubjects();

			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>((AbstractTableModel) current_table.getModel());
			// ref: https://stackoverflow.com/questions/29388366/merging-two-row-filters-for-a-jtable
			List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
			
			String text = parse_query(txtF.getText());
			if(text.isBlank() || text.isEmpty()) {
				sorter.setRowFilter(RowFilter.regexFilter(text));
				current_table.setRowSorter(sorter);
				return;
			}
			
			// rastavi query
			String[] query = text.split(", "); // ako ima whitespaces nakon parsiranja
			if(query.length == 1)
				query = text.split(","); // ako nema whitespaces
			
			System.out.println(query.length);
			
			switch(query.length) {
			case 1: // naziv
				sorter.setRowFilter(RowFilter.regexFilter(construct_query(query[0]), 1));
				break;
			case 2: // naziv+sifra
				filters.add(RowFilter.regexFilter(construct_query(query[0]), 1));
				filters.add(RowFilter.regexFilter(construct_query(query[1]), 0));
				sorter.setRowFilter(RowFilter.andFilter(filters));
				break;
			default:
				break;
			}
			
			current_table.setRowSorter(sorter);
		}
		
		private String parse_query(String s) {
			// brisanje trailing i leading whitespaces
			// ref: https://knpcode.com/java/java-basics/remove-spaces-from-string-java-trim-strip/
			String query = s.strip(); 
			
			// brisanje viska whitespaces u sredini stringa
			query = query.replaceAll("\\s{2,}", " ");
			return query.toLowerCase();
		}
		
		private String construct_query(String s) {
			return "(" + s.substring(0, 1).toUpperCase() + s.substring(1) + ")|(" + s + ")|(" + s.toUpperCase() +")";
		}
}
