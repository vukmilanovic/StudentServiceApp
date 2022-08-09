package view.tables;

import javax.swing.table.AbstractTableModel;

import data_base.StudentDB;
import view.main_frame.Main_window;

public class AbstractStudentTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2600082745058379120L;

	public AbstractStudentTableModel() {
	}

	@Override
	public int getRowCount() {
		return StudentDB.getInstance().getStudent_list().size();
	}

	@Override
	public int getColumnCount() {
		return StudentDB.getInstance().getColNum();
	}
	
	public String getColumnName(int column) {
		return StudentDB.getInstance().getColName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return StudentDB.getInstance().getValueAt(rowIndex, columnIndex);
	}
	
}
