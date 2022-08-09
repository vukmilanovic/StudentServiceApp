package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class UnpassedSubjects extends JTable {
	
	public UnpassedSubjects() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractUnpassedSubjectsTabelModel());
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
	
}
