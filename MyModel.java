import javax.swing.table.DefaultTableModel;

public class MyModel extends DefaultTableModel{
	
	public MyModel(Object[][] tableData, Object[] colNames){
		super(tableData, colNames);
	}
	
	public boolean isCellEditable(int row, int column){
		return false;
	}

}
