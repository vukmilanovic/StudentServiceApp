package view.tables;

import java.util.ArrayList;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import data_base.CourseClassDB;
import data_base.GradeDB;
import data_base.StudentDB;
import model_sistema.Grade;
import model_sistema.Student;
import view.main_frame.Main_Window_Tabs;
import view.main_frame.Main_window;

public class AbstractPassedSubjectsTableModel extends AbstractTableModel {
	
	private String[] cols = {getWord("codeTablePassed"), getWord("nameOfSTablePassed"), getWord("ESPBPointsTablePassed"), getWord("gradeTablePassed"), getWord("dateTablePassed")};
	
	int idx = Main_Window_Tabs.getInstance().getTableOfStudents().getSelectedRow();
	private Set<Grade> passedSubjects = StudentDB.getInstance().getRow(Main_Window_Tabs.getInstance().getTableOfStudents().convertRowIndexToModel(idx)).getPassed();
	
	public AbstractPassedSubjectsTableModel() {}

	@Override
	public int getRowCount() {
		return passedSubjects.size();
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
		ArrayList<Grade> temp = new ArrayList<Grade>(passedSubjects);
		
		Grade subject = temp.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return subject.getCourse().getCode();
		case 1:
			return subject.getCourse().getName();
		case 2:
			return subject.getCourse().getEspb();
		case 3:
			return subject.getGrade();
		case 4:
			return subject.getD();
		default:
			return null;
		}
	}

	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
}
