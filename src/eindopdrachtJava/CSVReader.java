package eindopdrachtJava;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

public class CSVReader {
	
	public String pathLink;
	DefaultTableModel model1 = new DefaultTableModel();
	
	//Setter for pathLink
	public CSVReader(String path) {
		this.pathLink = path;
	}
	
	//Load a CSV file into a DefaultTableModel
	public DefaultTableModel LoadCSV() {		
		
	try {
	  //Creating buffered reader object from the given file path
      BufferedReader reader = new BufferedReader(new FileReader("src\\" + this.pathLink + ".csv"));
      //First line are the column names
      String header = reader.readLine();
      //Make sure your tabs are separated by ; 
      String[] columnNames = header.split(";");
      model1.setColumnIdentifiers(columnNames);
      
      String line;
      //loop trough all the lines of the file while they are not NULL
      while ((line = reader.readLine()) != null) {
          String[] values = line.split(";");
          model1.addRow(values);
      }
      reader.close();
  } catch (IOException e) {
      e.printStackTrace();
  }
	//Return the DefaultTableModel object
	return(model1);	
	}
	
}
