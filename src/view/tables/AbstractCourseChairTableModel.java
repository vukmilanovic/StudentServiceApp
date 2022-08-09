package view.tables;

import javax.swing.table.AbstractTableModel;

import data_base.CourseChairDB;

public class AbstractCourseChairTableModel extends AbstractTableModel{

	private static final long serialVersionUID = -7053068322395792447L;

	public AbstractCourseChairTableModel() {
	}
	
	@Override
	public int getRowCount() {
		return CourseChairDB.getInstance().getChairs().size();
	}

	@Override
	public int getColumnCount() {
		return CourseChairDB.getInstance().getColNum();
	}

	public String getColumnName(int column) {
		return CourseChairDB.getInstance().getColName(column);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return CourseChairDB.getInstance().getValueAt(rowIndex, columnIndex);
	}

	
	
}
