package eindopdrachtJava;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;

public class ProductionLeadTime {
	JTable newTable;
	int highestProductionLead;
	String returnMessage;
	String productionLead;
	JTable openingHoursTable;
	
	//Setter for the JTables
	public ProductionLeadTime(JTable newTable, JTable openingHours) {
		this.newTable = newTable;
		this.openingHoursTable = openingHours;
	}
	
	//return a string that gives  the day and time when the order can be picked up
	public String getDeliveryTime() {
		//return an integer value named highestProductionLead that get the highest number within a column named ProductionLead 
		//ONLY when there is a value present inside a column named "Total  price"
		for (int i = 0; i < newTable.getRowCount(); i++) {
		    Object totalPrice = newTable.getValueAt(i, 5);
		    if (totalPrice != null) {
		        productionLead = (String) newTable.getValueAt(i, 3);
		        //Convert string to int, only take the first 2 characters so that you get the amount of hours the customer has to wait
		        this.highestProductionLead = Integer.parseInt(productionLead.substring(0, 2));
		    		    }
		}

		 // get the current date time
        Calendar currentTime = Calendar.getInstance();
        //Add highest production lead time to the current time
        currentTime.add(Calendar.HOUR_OF_DAY, highestProductionLead);
        //Get the day number from currentTime
        int dayNumber = currentTime.get(Calendar.DAY_OF_WEEK);

        //add new format in HH:mm (hour:minute)
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        //Get the pickup time in the new format
        String pickupTime = timeFormat.format(currentTime.getTime());
        
        //Get the opening hours and closing hours of the shop at the specific dayNumber
        //Extract 1 of the day number so that it matches the numbering of the columns in openingHoursTable
        String pickupDayOpening = openingHoursTable.getValueAt(dayNumber-1, 2).toString();
        String pickupDayClosing = openingHoursTable.getValueAt(dayNumber-1, 3).toString();

        //Check if the pickupTime is between the pickupDayOpening time and pickupDayClosing time
        //If not the order can be picked up the next day when the shop opens
        try {
            Date orderPickupTime = timeFormat.parse(pickupTime);
            Date shopOpen = timeFormat.parse(pickupDayOpening);
            Date shopClose = timeFormat.parse(pickupDayClosing);
            
            //If else statement for getting the right pickup day and time
            if (orderPickupTime.after(shopOpen) && orderPickupTime.before(shopClose)) {
            	//If pickup time is between shop opening time and closing time the order can be picked up during that day
              returnMessage = "Your order will be ready on " +  openingHoursTable.getValueAt(dayNumber-1, 1).toString() 
            		  + " after " + pickupTime + ". ";
   
            } else if(orderPickupTime.before(shopOpen)) {
              //If pickup time is before shop opening the order can be picked up at the start of the day
              returnMessage = "Your order will be ready on " +  openingHoursTable.getValueAt(dayNumber-1, 1).toString() 
              		  + " after " + pickupDayOpening + ". ";

            }else {
            	//If pickup time is after shop closing the order can be picked up at the start of next day
                returnMessage = "Your order will be ready on " +  openingHoursTable.getValueAt(dayNumber, 1).toString() 
                		  + " after " + pickupDayOpening + ". ";
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        //Return the message
		return returnMessage;
	}
	
	public int getHighestProductionLead() {
		return highestProductionLead;
	}
}
	
	

