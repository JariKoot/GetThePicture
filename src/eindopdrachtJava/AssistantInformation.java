package eindopdrachtJava;

import javax.swing.JTextField;

public class AssistantInformation {

	  private static String assistantName;
	  private static String assistantEmail;

	  //Setter
	  void AssistantInfo(
			  JTextField name, 
			  JTextField email) {
		  
		  assistantName = name.getText();
		  assistantEmail = email.getText();

	  }

	  //Getter for assistant name
	  public static String getAssistantName() {
	    return assistantName;
	  }
	  
	  //Getter for assistant email
	  public static String getAssistantEmail() {
	    return assistantEmail;
	  }

	}
