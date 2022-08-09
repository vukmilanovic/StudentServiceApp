package view.tables;

import javax.swing.table.AbstractTableModel;

import data_base.CourseClassDB;

public class AbstractSubjectTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 4174160915660404151L;

	public AbstractSubjectTableModel() {
	}

	@Override
	public int getRowCount() {
		return CourseClassDB.getInstance().getClasses().size();
	}

	@Override
	public int getColumnCount() {
		return CourseClassDB.getInstance().getMWColNum();
	}

	@Override
	public String getColumnName(int column) {
		return CourseClassDB.getInstance().getMWColName(column);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return CourseClassDB.getInstance().getValueAtMW(rowIndex, columnIndex);
	}

}
