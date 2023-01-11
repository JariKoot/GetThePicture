package eindopdrachtJava;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;


// Implement the TableModelListener interface
public class UpdateTable implements TableModelListener {
  private JTable table;

  public UpdateTable(JTable table) {
    this.table = table;
  }

  @Override
  public void tableChanged(TableModelEvent e) {
    // Check if the event was a change to the 4th column
    if (e.getColumn() == 4) {
      // Get the row that was changed
      int row = e.getFirstRow();
      // Get the value in the 4th of the changed row
   	    String stringValue = (String) table.getValueAt(row, 4);
   	    double value1 = Double.parseDouble(stringValue);
   	  // Get the value in the 2nd column of the changed row
 	    String stringValue2 = (String) table.getValueAt(row, 2);
 	    double value2 = Double.parseDouble(stringValue2);
	      // Update the second column based on the value in the first column
	      table.getModel().setValueAt(value2 * value1, row, 5);

    }
  }
}