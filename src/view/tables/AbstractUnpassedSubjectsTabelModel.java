package view.tables;

import java.util.ArrayList;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import data_base.StudentDB;
import model_sistema.CourseClass;
import model_sistema.IndexNum;
import model_sistema.Student;
import view.main_frame.Main_Window_Tabs;
import view.main_frame.Main_window;

public class AbstractUnpassedSubjectsTabelModel extends AbstractTableModel {

	private String[] cols = {getWord("codeTablePassed"), getWord("nameOfSTablePassed"), getWord("ESPBPointsTablePassed"), getWord("subjectTableCol3"), getWord("subjectTableCol4")};
	
	int idx = Main_Window_Tabs.getInstance().getTableOfStudents().getSelectedRow();
	private Set<CourseClass> unpassedSubjects = StudentDB.getInstance().getRow(Main_Window_Tabs.getInstance().getTableOfStudents().convertRowIndexToModel(idx)).getFailed();
	
	public AbstractUnpassedSubjectsTabelModel() {}
	
	@Override
	public int getRowCount() {
		return unpassedSubjects.size();
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
		ArrayList<CourseClass> temp = new ArrayList<CourseClass>(unpassedSubjects);
		
		CourseClass subject = temp.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return subject.getCode();
		case 1:
			return subject.getName();
		case 2:
			return subject.getEspb();
		case 3:
			return subject.getYear();
		case 4:
			return subject.getSem();
		default:
			return null;
		}
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
}
