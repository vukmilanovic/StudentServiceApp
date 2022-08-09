package view.tables;

import javax.swing.table.AbstractTableModel;

import data_base.ProfessorDB;

public class AbstractProfessorTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -1742167160236249184L;

	public AbstractProfessorTableModel() {
	}

	@Override
	public int getRowCount() {
		return ProfessorDB.getInstance().getProf_list().size();
	}

	@Override
	public int getColumnCount() {
		return ProfessorDB.getInstance().getColNum();
	}
	
	@Override
	public String getColumnName(int column) {
		return ProfessorDB.getInstance().getColName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return ProfessorDB.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
