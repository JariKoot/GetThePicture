package eindopdrachtJava;


//Before you launch the app, download PDFBox by following the 5 steps provided at the top of this page: https://www.codespeedy.com/generate-pdf-invoice-using-java/
//
import javax.swing.table.DefaultTableModel;

public class GetThePicture{
	MyFrame GetThePicture;
	static DefaultTableModel model = new DefaultTableModel();
	static DefaultTableModel testing = new DefaultTableModel();
	static CSVReader OpeningTimesImport;
	static DefaultTableModel openingTimes = new DefaultTableModel();
	static CSVReader priceListImport;
	static DefaultTableModel priceList = new DefaultTableModel();

	
	public GetThePicture() {
		//Create the application with the class MyFrame
		GetThePicture = new MyFrame(openingTimes, priceList);
	}
	
	public static void main(String[] args) {
		//Read the Opening Hours table 
		OpeningTimesImport = new CSVReader("PhotoShop_OpeningHours");
		openingTimes = OpeningTimesImport.LoadCSV();

		//Read the Price List table
		priceListImport = new CSVReader("PhotoShop_PriceList_WithColnames");
		priceList = priceListImport.LoadCSV();

        //Start the application
		GetThePicture openApplication = new GetThePicture();

}
		
	
	}
