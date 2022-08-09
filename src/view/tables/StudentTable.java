package view.tables;

import java.awt.Color;
import java.awt.Component;
import java.util.Comparator;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import data_base.StudentDB;
import model_sistema.IndexNum;
import view.main_frame.Main_window;

public class StudentTable extends JTable {

	//private static final long serialVersionUID = -5463699901651147897L;
	
	private AbstractStudentTableModel astm;

	public StudentTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.astm = new AbstractStudentTableModel();
		this.setModel(astm);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(astm);
		
		Comparator<IndexNum> comp = new Comparator<IndexNum>() {

			@Override
			public int compare(IndexNum o1, IndexNum o2) {
				return o1.getIdxnum() - o2.getIdxnum();
			}
		};
		
		sorter.setComparator(0, comp);
		this.setRowSorter(sorter);
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
		getColumnModel().getColumn(0).setHeaderValue(getWord("studentTableCol0"));
		getColumnModel().getColumn(1).setHeaderValue(getWord("studentTableCol1"));
		getColumnModel().getColumn(2).setHeaderValue(getWord("studentTableCol2"));
		getColumnModel().getColumn(3).setHeaderValue(getWord("studentTableCol3"));
		getColumnModel().getColumn(4).setHeaderValue(getWord("studentTableCol4"));
		getColumnModel().getColumn(5).setHeaderValue(getWord("studentTableCol5"));
	}
	
	public String getWord(String str) {
		return Main_window.getInstance().getResourceBundle().getString(str);
	}
}
