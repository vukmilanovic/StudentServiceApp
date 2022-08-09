package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import view.main_frame.Main_window;

public class SubjectTable extends JTable {

	public SubjectTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractSubjectTableModel());
		this.setAutoCreateRowSorter(true);
	}
	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component component = super.prepareRenderer(renderer, row, column);
		if(row % 2 == 0) {
			component.setBackground(Color.WHITE);
		} else {
			component.setBackground(new Color(255, 255, 153));
		}
		
		if(isRowSelected(row)) {
			component.setBackground(Color.LIGHT_GRAY);
		}
		
		return component;
	}
	
	public void initComponents() {
		getColumnModel().getColumn(0).setHeaderValue(getWord("subjectTableCol0"));
		getColumnModel().getColumn(1).setHeaderValue(getWord("subjectTableCol1"));
		getColumnModel().getColumn(2).setHeaderValue(getWord("subjectTableCol2"));
		getColumnModel().getColumn(3).setHeaderValue(getWord("subjectTableCol3"));
		getColumnModel().getColumn(4).setHeaderValue(getWord("subjectTableCol4"));
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
	
}
