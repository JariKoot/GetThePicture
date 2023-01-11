package eindopdrachtJava;

//Importing packages
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.miginfocom.swing.MigLayout;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class MyFrame extends JFrame{
	//Tabbed pane
	JTabbedPane tabbedPane = new JTabbedPane();
	//Tab1
	JPanel mainP1 = new JPanel();
    JPanel leftSideP1 = new JPanel();
    JPanel rightSideP1 = new JPanel();
    JTable tableOpeningHours = new JTable();
    DefaultTableModel openingHours = new DefaultTableModel();
    BufferedImage imageTab1 = null;
    JLabel imagePhotoCollection = new JLabel();
    JScrollPane scrollPaneOpeningHours = new JScrollPane();

    //tab2
    JPanel mainP2 = new JPanel();
    JPanel leftSideP2 = new JPanel();
    JPanel rightSideP2 = new JPanel();
    DefaultTableModel newModel = new DefaultTableModel();
    JLabel TotalItemCost = new JLabel(" ");
    JTable newTable = new JTable(newModel);
    JPanel topLeftP2 = new JPanel();
    BufferedImage imageTab2 = null;
    JLabel imageStoreItems = new JLabel();
    JPanel topRightP2 = new JPanel();
    JPanel bottomLeftP2 = new JPanel();
    JPanel bottomRightP2 = new JPanel();
    JTable tablePriceList = new JTable(); //Original price list table
    JTable shoppingCart = new JTable();
    DefaultTableModel PriceList = new DefaultTableModel();
    JComboBox<String> comboBox = new JComboBox<>();
    double totalSum;
    
    //tab3
    JPanel mainP3 = new JPanel();
    JTable totalCosts = new JTable();
    JTable totalCostTable = new JTable();
    DefaultTableModel totalTable = new DefaultTableModel(3, 2);
    JPanel leftSideP3 = new JPanel();
    JPanel rightSideP3 = new JPanel();
    String deliveryTimeMessage;
    double x = 0;
    double totalPrice = 0;
    JLabel createTotalTable = new JLabel("<html> To see the total value of your order: "
    		+ "<br/> Press the 'Calculate price' button in the 'Store' tab</html>");
    JLabel deliveryEstimationMessage = new JLabel("");
	JTextField customerNameField = new JTextField("");
	JTextField customerAddressField = new JTextField("");
	JTextField customerPostalCodeField = new JTextField("");
	JTextField customerPlaceField = new JTextField("");
	JTextField customerEmailTextField = new JTextField("");
	JTextField customerPhoneNumberField = new JTextField("");
	JLabel logo = new JLabel();
	BufferedImage logoTab3 = null;
	BufferedImage appLogo;
	BufferedImage imageLogo;
    private JFileChooser fileChooser;
	
	
	//MyFrame(DefaultTableModel priceListTable){
	MyFrame(DefaultTableModel OpeningHoursTable, DefaultTableModel priceListTable){
		System.out.println("---Starting application---");
		
	    //Creating fonts
		Font font1 = new Font("SansSerif", Font.PLAIN, 20);
		Font font2 = new Font("SansSerif", Font.BOLD, 30);
		Font font3 = new Font("SansSerif", Font.ITALIC, 20);
		Font font4 = new Font("SansSerif", Font.PLAIN, 16);
		Font font1Italic = new Font("SansSerif", Font.ITALIC, 18);
		
		
	    //_____________________________________________First tab: Shop Information______________________________________________
		//Creating the components of the left side, panel1
	    JLabel infoText1 = new JLabel("<html> <b> Welcom to Get The Picture!</b>"
	    		+ "<br/> In this app you will find the best prices for different types of photo frames." 
	    		+ "<br/> Right now you are looking at the home page of our application, feel free to check out the different tabs." 
	    		+ "<br/> In the store tab you can find all the items we sell, if your ready you can go to the checkout tab to finish your order."
	    		+ "<br/> On the checkout tab you can also see when your order will be ready for pickup."
	    		+ "<br/> This is possible within the opening hours of the store, see the table below for more information.</html>");
	    //Setting font
	    infoText1.setFont(font1);
	    
	    //Import table and modifying it before adding it to the main panel
	    tableOpeningHours.setModel(OpeningHoursTable);
	    tableLayout(tableOpeningHours);
	    tableOpeningHours.setEnabled(false);
	    //Remove first column because its not useful for the customers
        tableOpeningHours.getColumnModel().getColumn(0).setMinWidth(0);
        tableOpeningHours.getColumnModel().getColumn(0).setMaxWidth(0);
        scrollPaneOpeningHours = new JScrollPane(tableOpeningHours);

        //Creating the right side, panel1
        //Adding picture
        try {
          imageTab1 = ImageIO.read(new File("src\\figure\\PhotoCollection.jpg"));
        } catch (Exception e) {
          e.printStackTrace();
          System.exit(1);
        }
        imagePhotoCollection = new JLabel(new ImageIcon(imageTab1));

        
	    //Adding all the components to the left side
	    leftSideP1.setLayout(new MigLayout());
		leftSideP1.add(infoText1, "gapy 25px 337px, wrap");
        leftSideP1.add(scrollPaneOpeningHours, "pushx, growx");
        leftSideP1.setBackground(Color.white);
        //adding everything to main panel1
	    mainP1.setLayout(new GridLayout(1,2));
        mainP1.add(leftSideP1);        
        mainP1.add(imagePhotoCollection);

	    
	    //_____________________________________________Second tab: Store________________________________________________________
	    //Set layout of rightside P2
	    rightSideP2.setLayout(new MigLayout());
	    
	    //Creating elements
	    JLabel textField1 = new JLabel("Our items"); 
	    JLabel shoppingCartText = new JLabel("Shopping Cart");
	    JButton calculatePrice = new JButton("Calculate price"); 
	    
	    //Item table
	    tablePriceList.setModel(priceListTable);
	    tableLayout(tablePriceList);
	    JScrollPane scrollPriceList = new JScrollPane();
	    scrollPriceList = new JScrollPane(tablePriceList);
	    
        // Get the column data as a list of Strings
        List<String> columnData = new ArrayList<>();
        for (int i = 0; i < tablePriceList.getRowCount(); i++) {
            columnData.add((String) tablePriceList.getValueAt(i, tablePriceList.getColumn("Description").getModelIndex()));
        }

     // Copy the column names from the original table
     for (int i = 0; i < tablePriceList.getColumnCount(); i++) {
         newModel.addColumn(tablePriceList.getColumnName(i));
     }

     // Add the new "Amount" and "Total price" column to the end of the table
     newModel.addColumn("Amount");
     newModel.addColumn("Total price");

     // Copy the data from the original table
     for (int i = 0; i < tablePriceList.getRowCount(); i++) {
         Object[] rowData = new Object[tablePriceList.getColumnCount() + 1];
         for (int j = 0; j < tablePriceList.getColumnCount(); j++) {
             rowData[j] = tablePriceList.getValueAt(i, j);
         }
         // Set the default value for the "Amount" column
         rowData[tablePriceList.getColumnCount()] = 0;
         newModel.addRow(rowData);
     }

     
     TableModel model = newTable.getModel();
     model.addTableModelListener(new UpdateTable(newTable));
   
     //Hide unwanted columns for the user
     newTable.getColumnModel().getColumn(0).setMinWidth(0);
     newTable.getColumnModel().getColumn(0).setMaxWidth(0);
     newTable.getColumnModel().getColumn(3).setMinWidth(0);
     newTable.getColumnModel().getColumn(3).setMaxWidth(0);
     tableLayout(newTable);
  
     //Create the scrollshoppingcart table
     JScrollPane scrollShoppingCart = new JScrollPane();
     scrollShoppingCart = new JScrollPane(newTable);
     scrollShoppingCart.setEnabled(false);

     //Creating the right side, panel2
     //Adding picture
     try {
       imageTab2 = ImageIO.read(new File("src\\figure\\itemsStore.jpg"));
     } catch (Exception e) {
       e.printStackTrace();
       System.exit(1);
     }
     imageStoreItems = new JLabel(new ImageIcon(imageTab2));

     	//Set fonts
		 TotalItemCost.setFont(font3);
	     textField1.setFont(font2);
	     shoppingCartText.setFont(font2);
     
     	//Add left side components
		leftSideP2.setLayout(new MigLayout());
	    leftSideP2.add(imageStoreItems);
	    leftSideP2.setBackground(Color.white);

	    //Add right side components
	    rightSideP2.add(shoppingCartText, "wrap, gapy 110px 5px, gapx 30px");
	    rightSideP2.add(scrollShoppingCart, "height 251, width 700, wrap, gapx 20px");
	    rightSideP2.add(calculatePrice, "wrap, height 40, width 300, gapy 10px 10px, gapx 200px");
	    rightSideP2.add(TotalItemCost);
	    rightSideP2.setBackground(Color.white);
	    //rightSideP2.setBorder(BorderFactory.createLineBorder(Color.black));
	    
	    //Add main page components
	    mainP2.setLayout(new GridLayout(1,2));
	    mainP2.add(leftSideP2);
        mainP2.add(rightSideP2);

     	//Calculate the total price of the shopping cart
	    calculatePrice.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	           //double sum = 0;
	            totalSum = 0;
	            for (int i = 0; i < newTable.getRowCount(); i++) {
	           	    Double stringValue = (Double) newTable.getValueAt(i, 5);
	           	    
	           	    if(stringValue != null) {
	           	    totalSum += stringValue;
	           	    }
	            }
	            
	           //Set the total order value text and fill in the table where the total values are visualized
	           TotalItemCost.setText("Your order has a total value of: €" + totalSum);
	           createTotalTable.setText("");
	           totalTable.setValueAt(totalSum, 0, 1);
	           totalTable.setValueAt(0, 1, 1);
	           totalTable.setValueAt(totalSum, 2, 1);
	           
	           //Calculate delivery time after the order is calculated
	           System.out.println("Calculating production time...");
	           ProductionLeadTime message = new ProductionLeadTime(newTable, tableOpeningHours);
	           //Print the delivery time message
		       deliveryTimeMessage = message.getDeliveryTime();
	           deliveryEstimationMessage.setText(deliveryTimeMessage); 
	           System.out.println("Production time calculated");
	           
	           
	           Object cellValue = totalTable.getValueAt(2, 1);
	           //If there is a value in cell 2,1 print the delivery message, otherwise give a warning that tells the user to fill in a value
	           if (cellValue != null) {
	               double value = (double) cellValue;
	               if (value > 0) {
	                   deliveryEstimationMessage.setText(deliveryTimeMessage); 
	               } else if (value == 0) {
	            	   deliveryEstimationMessage.setText("Please make sure that you have selected at least one item.");	   
	               }
	           }
	        }
	    });
	    

	    //_____________________________________________Third tab: Checkout______________________________________________________

        //Create a table that shows the user the total value of there order
        // Set the values of the first 3 rows of the first column
        totalTable.setValueAt("Price", 0, 0);
        totalTable.setValueAt("Delivery Costs", 1, 0);
        totalTable.setValueAt("Total price", 2, 0);
  
        try {
			appLogo = ImageIO.read(new File("src\\\\figure\\\\logoGTP.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        JPanel panelLogo = new JPanel() {
            @Override
            public void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                g.drawImage(appLogo, 0, 0, 300, 200, null);
            }
        };
        panelLogo.setPreferredSize(new Dimension(300, 200));
        
        totalCosts = new JTable(totalTable);
		JLabel orderInfoText = new JLabel("Order information"); 
		JLabel ShopAssistantText = new JLabel("Shop assistant"); 
		JLabel customerInfoText = new JLabel("Customer information");
		JButton placeOrderButton = new JButton("Place order");
		JButton loadPDF = new JButton("Upload order");
		JLabel AssistantNameText = new JLabel("Name");
		JLabel AssistantEmailText= new JLabel("Email-address");
		final JTextField AssistantNameField = new JTextField("Jari Koot ");
		final JTextField AssistantEmailField = new JTextField("jari.koot@calco.nl ");
		JLabel customerNameText = new JLabel("Name");
		JLabel customerAddress = new JLabel("Address");
		JLabel customerPostalCode = new JLabel("Postal Code");
		JLabel customerPlace = new JLabel("Place");
		JLabel customerEmailText= new JLabel("Email-address");
		JLabel customerPhoneNumber = new JLabel("Mobile Number");

		//Settings
		//tableLayout(totalCosts);
	    //Import table
		totalCosts.setFont(new Font("Arial", Font.BOLD, 20));
		totalCosts.setRowHeight(31);
		totalCosts.getColumnModel().getColumn(0).setMinWidth(150);
		totalCosts.getColumnModel().getColumn(0).setMaxWidth(150);
		totalCosts.getColumnModel().getColumn(1).setMinWidth(150);
		totalCosts.getColumnModel().getColumn(1).setMaxWidth(150);
		totalCosts.setEnabled(false);
		
		// Get the first column of the table
		TableColumn column = totalCosts.getColumnModel().getColumn(0);

		// Set the cell renderer for the column to a new instance of DefaultTableCellRenderer
		column.setCellRenderer(new DefaultTableCellRenderer() {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        // Call the super method to get the default renderer component
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        Font fontTable = new Font("SansSerif", Font.PLAIN, 20);
		        c.setForeground(new Color(255, 255, 255));
		        c.setFont(fontTable);
		        c.setBackground(new Color(32,136,203));

		        // Return the modified component
		        return c;
		    }
		});

		// Repaint the table to reflect the change
		totalCosts.repaint();

		//Make the 2nd column (column1) allign to the right
		TableColumn column1 = totalCosts.getColumnModel().getColumn(1);
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.RIGHT);
		column1.setCellRenderer(renderer);
		
		//totalCosts.setBorder(BorderFactory.createTitledBorder("Table Border"));
		totalCosts.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		//textfield settings
		AssistantNameField.setHorizontalAlignment(SwingConstants.RIGHT);
		AssistantNameField.setEditable(false);
		AssistantEmailField.setHorizontalAlignment(SwingConstants.RIGHT);
		AssistantEmailField.setEditable(false);

		
		//Fonts
		orderInfoText.setFont(font2);
		ShopAssistantText.setFont(font2);
		customerInfoText.setFont(font2);
		placeOrderButton.setFont(font3);
		loadPDF.setFont(font3);
		AssistantNameText.setFont(font1);
		AssistantEmailText.setFont(font1);
		AssistantNameField.setFont(font1Italic);
		AssistantEmailField.setFont(font1Italic);
		customerNameText.setFont(font1);
		customerAddress.setFont(font1);
		customerPostalCode.setFont(font1);
		customerPlace.setFont(font1);
		customerEmailText.setFont(font1);
		customerPhoneNumber.setFont(font1);
		customerNameField.setFont(font1Italic);
		customerAddressField.setFont(font1Italic);
		customerPostalCodeField.setFont(font1Italic);
		customerPlaceField.setFont(font1Italic);
		createTotalTable.setFont(font1);
		deliveryEstimationMessage.setFont(font4);
		customerEmailTextField.setFont(font1Italic);
		customerPhoneNumberField.setFont(font1Italic);
		
		//Leftside components
		leftSideP3.setLayout(new MigLayout());
		leftSideP3.add(orderInfoText, "wrap, gapy 20px 20px");
		leftSideP3.add(createTotalTable, "wrap, gapx 20px");
		leftSideP3.add(totalCosts, "wrap, gapx 20px");
		leftSideP3.add(deliveryEstimationMessage, "wrap, gapy 0px 200px, gapx 20px");
		leftSideP3.add(ShopAssistantText, "gapy 35px, wrap");
		leftSideP3.add(AssistantNameText, "gapx 20px 10px, gapy 20px");
		leftSideP3.add(AssistantNameField, "height 35, width 280, wrap, gapx 0px 0px");
		leftSideP3.add(AssistantEmailText, "gapx 20px");
		leftSideP3.add(AssistantEmailField, "height 35, width 280, wrap, gapx 0px 0px");
		//Leftside settings
		leftSideP3.setBackground(Color.white);
		
		//Rightside components
		rightSideP3.setLayout(new MigLayout());
		rightSideP3.add(customerInfoText, "gapy 20px, wrap");
		rightSideP3.add(customerNameText, "gapy 20px 0px, gapx 20px");
		rightSideP3.add(customerNameField, "height 35, width 280, wrap");
		rightSideP3.add(customerAddress, "gapx 20px");
		rightSideP3.add(customerAddressField, "height 35, width 280, wrap");
		rightSideP3.add(customerPostalCode, "gapx 20px");
		rightSideP3.add(customerPostalCodeField, "height 35, width 280, wrap");
		rightSideP3.add(customerPlace, "gapx 20px");
		rightSideP3.add(customerPlaceField, "height 35, width 280, wrap");
		rightSideP3.add(customerEmailText, "gapx 20px");
		rightSideP3.add(customerEmailTextField, "height 35, width 280, wrap");
		rightSideP3.add(customerPhoneNumber, "gapx 20px");
		rightSideP3.add(customerPhoneNumberField, "height 35, width 280, wrap");
		rightSideP3.add(placeOrderButton, "height 40, width 270, gapy 30px 10px, gapx 30px");
		rightSideP3.add(loadPDF, "wrap, height 40, width 270, gapy 10px 10px, gapx 2px");
		rightSideP3.add(panelLogo, "gapy 100, gapx 200px");
		//Rightside settings
		rightSideP3.setBackground(Color.white);

		
	    //Add main page components
	    mainP3.setLayout(new GridLayout(1,2));
	    mainP3.add(leftSideP3);
        mainP3.add(rightSideP3);
		mainP3.setBackground(Color.white);
		
		//Add all the tabbed panes
		tabbedPane.addTab("Shop Information", mainP1);
	    tabbedPane.addTab("Store", mainP2);
	    tabbedPane.addTab("Checkout", mainP3);
	    
	    //Set logo of app
		try {
			imageLogo = ImageIO.read(new File("src\\\\\\\\figure\\\\\\\\appLogo.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    //Order button aciton listener
	    placeOrderButton.addActionListener(new ActionListener() {
        	String orderStatus = "You have to order at least one item.";

	    	@Override
	        public void actionPerformed(ActionEvent e) {

	        	//Check if there is a value inside the totalTable
		           Object cellValue = totalTable.getValueAt(2, 1);
		           //If there is a value in cell 2,1 print the delivery message, otherwise give a warning that tells the user to fill in a value
		           if (cellValue != null) {
		               double value = (double) cellValue;
		               if (value > 0) {
		   	        	//Check if all the text fields are filled in 
		   	        	if (customerNameField.getText().isEmpty() 
		   	        			|| customerAddressField.getText().isEmpty() 
		   	        			|| customerPostalCodeField.getText().isEmpty() 
		   	        			|| customerPlaceField.getText().isEmpty() 
		   	        			|| customerEmailTextField.getText().isEmpty() 
		   	        			|| customerPhoneNumberField.getText().isEmpty()) {
		   	        	  orderStatus = "Please fill in all the text fields";
		   	        	} else {
		   	        	  orderStatus = "Invoice is created";
		   	        //Fill in the customer information 
		   	   		CustomerInformation customer = new CustomerInformation();
		   			customer.CustomerInfo(customerNameField, customerAddressField, customerPostalCodeField, customerPlaceField, customerEmailTextField, customerPhoneNumberField);
		   			
		   			//Fill in the assistant information
		   			AssistantInformation assistant = new AssistantInformation();
		   			assistant.AssistantInfo(AssistantNameField, AssistantEmailField);
		   			
					//Create the invoice
		   			System.out.println("Creating invoice...");
					CreateInvoice newInvoice = new CreateInvoice(newTable, totalSum, deliveryTimeMessage);
					System.out.println("Invoice is created");
		   	        	}	   
		               } else {
		 	        	  orderStatus = "You have to order at least one item.";  	   
		               }
		           }
  	
	            JOptionPane.showMessageDialog(null, orderStatus);
	        }
	        });

	    fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF files", "pdf"));

	    
	    loadPDF.addActionListener(new ActionListener() {
	    	@Override
	        public void actionPerformed(ActionEvent e) { 
	    	    // Create a file dialog to allow the user to choose a PDF file
	    	    FileDialog fileDialog = new FileDialog(new JFrame(), "Choose a XML file", FileDialog.LOAD);
	    	    fileDialog.setDirectory(".");
	    	    fileDialog.setFile("*.xml");
	    	    fileDialog.setVisible(true);
	    	    String fileName = fileDialog.getFile();
	    	    System.out.println(fileName);
	    	    //Read out the xml file
	    	    System.out.println("Importing XML file...");
	    	    ReadXML readXMLFile = new ReadXML(fileName);
	    	    System.out.println("Imported XML file");
	    	    
	    	    //Fill in the customer details based on the imported XML file
	    	    customerNameField.setText(readXMLFile.getCustNameXML());
	    	    customerAddressField.setText(readXMLFile.getCustAddressXML());
	    	    customerPostalCodeField.setText(readXMLFile.getCustPostalCodeXML());
	    	    customerPlaceField.setText(readXMLFile.getCustPlaceXML());
	    	    customerEmailTextField.setText(readXMLFile.getCustEmailXML());
	    	    customerPhoneNumberField.setText(readXMLFile.getCustNumberXML());
	    	    
	    	    //Fill in the shoppingcart table based on the imported XML file
	    	    //Extract ItemNR
	    	    ArrayList<Integer> extractedItemNrXML= new ArrayList<Integer>();
	    	    extractedItemNrXML =  readXMLFile.getItemNumberXML();
	    	    //Extract Amount 
	    		ArrayList<Double> extractedAmountXML = new ArrayList<Double>();
	    		extractedAmountXML = readXMLFile.getAmountXML();
	    		 
    			 //Set the value inside the shoppingcart table (in second tab)
	    		 for (int i = 0; i < extractedItemNrXML.size(); i++) {
	    			 String stringValue = Double.toString(extractedAmountXML.get(i));
	    			 //Set the value inside the shoppingcart table (in second tab)
	    			 newTable.setValueAt(stringValue, extractedItemNrXML.get(i)-1, 4);
	    			}
	    		   	    
	    	}
	    	});
    
	    //_____________________________________________Frame settings___________________________________________________________
		this.setTitle("Get The Picture");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.add(tabbedPane);
	    this.getContentPane().setBackground(Color.white);
	    this.setIconImage(imageLogo);
	    
	    // Display the frame
	    this.setVisible(true);
	} 
	
	
	public void tableLayout(JTable table) {
	    table.getTableHeader().setOpaque(false);
	    table.setRowHeight(19);
	    table.getTableHeader().setBackground(new Color(32,136,203));
	    table.getTableHeader().setForeground(new Color(255, 255, 255));
	    table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
	}

}




