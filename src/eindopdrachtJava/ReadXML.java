package eindopdrachtJava;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ReadXML {
	
	XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
	String filePathXML;
	String custName;
	String custAddress;
	String custPostalCode;
	String custPlace;
	String custEmail;
	String custNumber;
	 ArrayList<Integer> extractedValuesColumn0 = new ArrayList<Integer>();
	 ArrayList<Double> extractedValuesColumn4 = new ArrayList<Double>();
	 
	public ReadXML(String fileName) {
		this.filePathXML = fileName;

        try {
        	//Create stream reader
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(filePathXML));
            
            //Itterate over all the values with a while loop
            while (xmlStreamReader.hasNext()) {
            	
                int event = xmlStreamReader.next();
                if (event == XMLStreamReader.START_ELEMENT) {
                    String elementName = xmlStreamReader.getLocalName();
                    if (elementName.equals("custName")) {
                        String custName = xmlStreamReader.getElementText();
                        this.custName = custName;
                    } else if (elementName.equals("custAddress")) {
                        String custAddress = xmlStreamReader.getElementText();
                        this.custAddress = custAddress;
                    } else if (elementName.equals("custPostalCode")) {
                        String custPostalCode = xmlStreamReader.getElementText();
                        this.custPostalCode = custPostalCode;
                    } else if (elementName.equals("custPlace")) {
                        String custPlace = xmlStreamReader.getElementText();
                        this.custPlace = custPlace;
                    } else if (elementName.equals("custEmail")) {
                        String custEmail = xmlStreamReader.getElementText();
                        this.custEmail = custEmail;
                    } else if (elementName.equals("custNumber")) {
                        String custNumber = xmlStreamReader.getElementText();
                        this.custNumber = custNumber;
                    } 
                    //Extract the column 0 values (Item Number)
                    else if (elementName.startsWith("row") && elementName.endsWith("column0")) {
                             //Add the element name to the ArrayList
                    	String column0Text = xmlStreamReader.getElementText();
                    	Integer column0Int = Integer.parseInt(column0Text);
                    	extractedValuesColumn0.add(column0Int);
                        }
                  //Extract the column 0 values (Item Number)
                    else if (elementName.startsWith("row") && elementName.endsWith("column4")) {
                             //Add the element name to the ArrayList
                    	String column4Text = xmlStreamReader.getElementText();
                    	Double column4Int = Double.parseDouble(column4Text);
                    	extractedValuesColumn4.add(column4Int);                        }
                    } 
      
            }          

    
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
	}
	
	public String getCustNameXML() {
		return custName;
	}
	
	public String getCustAddressXML() {
		return custAddress;
	}
	
	public String getCustPostalCodeXML() {
		return custPostalCode;
	}
	
	public String getCustPlaceXML() {
		return custPlace;
	}
	
	public String getCustEmailXML() {
		return custEmail;
	}
	
	public String getCustNumberXML() {
		return custNumber;
	}
	
	public ArrayList<Integer> getItemNumberXML(){
		return extractedValuesColumn0;
	}
	
	public ArrayList<Double> getAmountXML(){
		return extractedValuesColumn4;
	}

}
		
		
		
		
		

