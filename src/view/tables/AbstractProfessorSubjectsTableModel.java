package view.tables;

import java.util.ArrayList;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import data_base.CourseClassDB;
import data_base.ProfessorDB;
import model_sistema.CourseClass;
import model_sistema.Professor;
import view.main_frame.Main_Window_Tabs;
import view.main_frame.Main_window;

public class AbstractProfessorSubjectsTableModel extends AbstractTableModel {

	private String[] cols = {getWord("codeTablePassed"), getWord("nameOfSTablePassed"), getWord("subjectTableCol3"), getWord("subjectTableCol4")};
	
	int idx = Main_Window_Tabs.getInstance().getTableOfProfessors().getSelectedRow();
	private Set<CourseClass> classes = ProfessorDB.getInstance().getRow(Main_Window_Tabs.getInstance().getTableOfProfessors().convertRowIndexToModel(idx)).getCourses(); 
	// niz obj
	
	@Override
	public int getRowCount() {
		return classes.size();
	}

	@Override
	public int getColumnCount() {
		return cols.length;
	}

	public String getColumnName(int column) {
		return cols[column];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ArrayList<CourseClass> temp = new ArrayList<CourseClass>(classes);
		
		CourseClass cls = temp.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return cls.getCode();
		case 1:
			return cls.getName();
		case 2:
			return cls.getYear();
		case 3:
			return cls.getSem();
		default:
			return null;
		}
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}

}
