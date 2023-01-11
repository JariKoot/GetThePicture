package eindopdrachtJava;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JTable;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class CreateInvoice {

	public static JTable shoppingCartTable = new JTable();
    public static PdfPTable tablePrint = new PdfPTable(3);
    JTable tableImport = new JTable();
    PdfPTable pdfTable = new PdfPTable(6);
    
    //Create invoice
	public CreateInvoice(JTable newTable, double totalsum, String deliveryTimeMessage){
		
		shoppingCartTable = newTable;
        // Create a new document
        Document document = new Document();
        // Create a fonts
        Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
        Font fontBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font fontNormal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        try {
            // Get the output stream for the PDF document
            OutputStream outputStream = new FileOutputStream(new File("invoiceGTP.pdf"));

            // Create a PDF writer object
            PdfWriter.getInstance(document, outputStream);

            // Open the document
            document.open();
            
            // Add the title and image to the document
            document.add(new Paragraph("Get The Picture - Invoice", fontTitle));
            document.add(new Paragraph("\n"));
            //Add the image
            Image image = Image.getInstance("src\\figure\\logoGTP.png");
            image.scaleToFit(300, 200);
            image.setAbsolutePosition(0, 0);
            document.add(image);
            
            // Add the customer information
            document.add(new Paragraph("Customer information:", fontBold));
            document.add(new Paragraph(CustomerInformation.getCustName(), fontNormal));
            document.add(new Paragraph(CustomerInformation.getCustAddress(), fontNormal));
            document.add(new Paragraph(CustomerInformation.getCustPostalCode(), fontNormal));
            document.add(new Paragraph(CustomerInformation.getCustPlace(), fontNormal));
            document.add(new Paragraph(CustomerInformation.getCustEmail(), fontNormal));
            document.add(new Paragraph(CustomerInformation.getCustNumber(), fontNormal));

            // Add the shop assistant information
            document.add(new Paragraph("Assistant information:", fontBold));
            document.add(new Paragraph(AssistantInformation.getAssistantName(), fontNormal));
            document.add(new Paragraph(AssistantInformation.getAssistantEmail(), fontNormal));

            //Order information
            document.add(new Paragraph("\n"));
            // get the current datetime and give it in the format: MM-dd-yyyy HH:mm
            Calendar calendar = Calendar.getInstance();
            // Create a SimpleDateFormat object with the desired date and time format
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
            // Format the date and time and print it to the console
            document.add(new Paragraph("Order Number: 42004, generated at: " + dateFormat.format(calendar.getTime()), fontBold));
            //Add the pick up time 
            document.add(new Paragraph(deliveryTimeMessage, fontBold));
            document.add(new Paragraph("\n"));
            
            //Create the table of all the ordered items
            tableImport = shoppingCartTable;
            // Create a new PdfPTable object and copy the column names from the original table
            //Column names table
            PdfPTable columnNames = new PdfPTable(6);
            columnNames.addCell("Item number");
            columnNames.addCell("Description");
            columnNames.addCell("Price");
            columnNames.addCell("Production time");
            columnNames.addCell("Amount");
            columnNames.addCell("Total price");
            document.add(columnNames);
            //Copy the content of tableImport only when there is a value present in column number 5 (Amount)
            for (int i = 0; i < tableImport.getRowCount(); i++) {
                // Check if there is a value present in column number 3
                if (tableImport.getValueAt(i, 5) != null) {
                    for (int j = 0; j < tableImport.getColumnCount(); j++) {
                    	pdfTable.addCell(tableImport.getValueAt(i, j).toString());
                    }
                }
            }
            

            //Add the table
            document.add(pdfTable);
            document.add(new Paragraph("\n"));
            //Give total price
            double totalPrice = totalsum;
            document.add(new Paragraph("The total price of your order is: €" + totalPrice));
            document.add(new Paragraph("\n"));
            //Last lines with information about when the order has to be payed
            document.add(new Paragraph("Please make sure the payment is done within 30 days, add the order number to the check. "));
            document.add(new Paragraph("If you have any questions about this invoice or your order, contact us at getyourpicture@gmail.com."));

            // Close the document
            document.close();

            // Flush the output stream
            outputStream.flush();

            // Close the output stream
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Create XML
        System.out.println("Creating XML file...");
        createXML();
        System.out.println("XML file has been created");

    }
	
	public void createXML() {
		XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
		try {
		    XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream("invoiceGTP.xml"), "UTF-8");
		    xmlStreamWriter.writeStartDocument();
		    xmlStreamWriter.writeStartElement("root");

		    xmlStreamWriter.writeStartElement("custName");
		    xmlStreamWriter.writeCharacters(CustomerInformation.getCustName());
		    xmlStreamWriter.writeEndElement();

		    xmlStreamWriter.writeStartElement("custAddress");
		    xmlStreamWriter.writeCharacters(CustomerInformation.getCustAddress());
		    xmlStreamWriter.writeEndElement();

		    xmlStreamWriter.writeStartElement("custPostalCode");
		    xmlStreamWriter.writeCharacters(CustomerInformation.getCustPostalCode());
		    xmlStreamWriter.writeEndElement();
		    
		    xmlStreamWriter.writeStartElement("custPlace");
		    xmlStreamWriter.writeCharacters(CustomerInformation.getCustPlace());
		    xmlStreamWriter.writeEndElement();
		    
		    xmlStreamWriter.writeStartElement("custEmail");
		    xmlStreamWriter.writeCharacters(CustomerInformation.getCustEmail());
		    xmlStreamWriter.writeEndElement();
		    
		    xmlStreamWriter.writeStartElement("custNumber");
		    xmlStreamWriter.writeCharacters(CustomerInformation.getCustNumber());
		    xmlStreamWriter.writeEndElement();
		    
		    xmlStreamWriter.writeStartElement("ItemNumber");
		    xmlStreamWriter.writeCharacters(CustomerInformation.getCustNumber());
		    xmlStreamWriter.writeEndElement();
		    
		    xmlStreamWriter.writeStartElement("table");
		    //Create number to keep track on how many rows there are
		    int rowNumber = 1;
		    
		 // Iterate over the rows
            for (int row = 0; row < tableImport.getRowCount(); row++) {
                // Check if there is a value present in column number 5
                if (tableImport.getValueAt(row, 5) != null) {
                	for (int col = 0; col < tableImport.getColumnCount(); col++) {
                    	//Get values in column number 0 and 4
                        if (col == 0 || col == 4) {
                            // Write the column to the XML document
                        	xmlStreamWriter.writeStartElement("row" + rowNumber + "column" + col);
                        	Object value = tableImport.getValueAt(row, col);
                            	xmlStreamWriter.writeCharacters(value.toString());
                            xmlStreamWriter.writeEndElement();
                        }                        

                    }	rowNumber++;

                }
            }

		    xmlStreamWriter.writeEndElement();
		    xmlStreamWriter.writeEndDocument();
		    xmlStreamWriter.flush();
		    xmlStreamWriter.close();
		} catch (XMLStreamException | IOException e) {
		    e.printStackTrace();
		}
	}
}