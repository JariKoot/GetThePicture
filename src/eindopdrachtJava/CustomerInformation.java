package eindopdrachtJava;

import javax.swing.JTextField;

public class CustomerInformation {

	  private static String custName;
	  private static String custAddress;
	  private static String custPostalCode;
	  private static String custPlace;
	  private static String custEmail;
	  private static String custNumber;

	  //Setter
	  void CustomerInfo(
			  JTextField name, 
			  JTextField address, 
			  JTextField postalCode, 
			  JTextField place, 
			  JTextField email, 
			  JTextField number) {
		//Set all the local variables
	    custName = name.getText();
	    custAddress = address.getText();
	    custPostalCode = postalCode.getText();
	    custPlace = place.getText();
	    custEmail = email.getText();
	    custNumber = number.getText();
	  }
	  
	  //Getters for local variables
	  public static String getCustName() {
	    return custName;
	  }

	  public static String getCustAddress() {
	    return custAddress;
	  }

	  public static String getCustPostalCode() {
	    return custPostalCode;
	  }

	  public static String getCustPlace() {
	    return custPlace;
	  }

	  public static String getCustEmail() {
	    return custEmail;
	  }

	  public static String getCustNumber() {
	    return custNumber;
	  }
	}
